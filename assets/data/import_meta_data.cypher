// 1. 导入庐山景点信息
LOAD CSV WITH HEADERS FROM 'file:///NODE_attraction.csv' AS csv 
CREATE (c :Attraction {
  attractionId: toInteger(csv.attractionId),
  name: toString(csv.name),
  latitude: TOFLOAT(csv.lat),
  longitude: TOFLOAT(csv.lng),
  category: toString(csv.category),
  capacity: toInteger(csv.capacity),
  current: toInteger(csv.current),
  status: toInteger(csv.status),
  parentId: toInteger(csv.parentId)
})


// 2. 导入步行开销关系（双向）
LOAD CSV WITH HEADERS
  FROM 'file:///walking.csv'
  AS csv
MATCH (a1:Attraction {attractionId: toInteger(csv.ANode)})
MATCH (a2:Attraction {attractionId: toInteger(csv.BNode)})
MERGE (a1) -[:WALKING {cost: toFloat(csv.cost), distance: toFloat(csv.distance), capacity: toInteger(csv.road_capacity), current: toInteger(csv.road_current)}]-(a2)

// 3. Attraction 设置路线正常开销
match (n:Attraction)-[r:WALKING]-(n1:Attraction) set r.normalCost = r.cost

// 4. 根据实际情况初始实际开销
MATCH ()-[r:WALKING]->(n:Attraction)
SET r.cost = 
  CASE WHEN n.current / toFloat(n.capacity) >= 0.85 
    THEN r.normalCost * (1 / (1 - n.current / toFloat(n.capacity)))
    ELSE r.normalCost 
  END

// 5. 创建原生图投影
CALL gds.graph.create('single-source', 'Attraction', {WALKING: {properties: ['cost','distance','current','capacity','normalCost']}})


// 6. 预先处理所有景点之间的 K 最短路
match (a:Attraction) 
with collect(a) as attractions
UNWIND attractions as a1
with a1,
    [a in attractions] as a2s,
    attractions
unwind a2s as a2
CALL gds.shortestPath.yens.stream('single-source', {
    sourceNode: a1,
    targetNode: a2,
    k: 3,
    relationshipWeightProperty: 'cost'
})
YIELD index, sourceNode, targetNode, totalCost, nodeIds
WITH index, sourceNode, targetNode, totalCost, nodeIds,
reduce(s = "", x IN nodeIds | s + case when s <> '' then ' ' else '' end + gds.util.asNode(x).attractionId)  as viaPath
WHERE totalCost > 0.0 and sourceNode <> targetNode
match (n1: Attraction) WHERE ID(n1) = sourceNode
match (n2: Attraction) WHERE ID(n2) = targetNode
merge (n1)-[r:SHORTEST_PATH]->(n2)
	ON CREATE SET r.costs = [totalCost]
	ON CREATE SET r.viaPaths = [viaPath]
	ON MATCH SET r.costs = coalesce(r.costs, []) + totalCost
	ON MATCH SET r.viaPaths = coalesce(r.viaPaths, []) + viaPath
WITH r, index, nodeIds
where index = 0 SET r.bestPath = nodeIds

// 7. 删除投影，释放内存
CALL gds.graph.drop('single-source')

// 8. 求任意两个景点的最短路径
MATCH (a:Attraction)-[r:SHORTEST_PATH]-(b:Attraction)
WHERE a.attractionId = b.attractionId
return r.bestPath, r.costs[0] 

// 9. 根据选中景点求哈密顿路径
WITH [1021,1022,1023,1025] as selection
MATCH (a:Attraction) where a.attractionId in selection
WITH collect(a) as attractions
UNWIND attractions as a1
WITH a1,
     [a in attractions where a.attractionId > a1.attractionId] as a2s,
     (size(attractions) - 1) as level,
     attractions
UNWIND a2s as a2
CALL apoc.path.expandConfig(a1, {
    relationshipFilter: 'SHORTEST_PATH>',
    minLevel: level,
    maxLevel: level,
    whitelistNodes: attractions,
    terminatorNode: [a2],
    uniqueness: 'NODE_PATH'
}) YIELD path
WITH nodes(path) as orderedAttractions,
    [n in nodes(path) | id(n)] as ids,
    reduce (cost = 0, x in relationships(path) | cost + x.costs[0]) as totalCost,
    [r in relationships(path) | r.bestPath] as shortestRouteNodeIds
    order by totalCost LIMIT 1
UNWIND range(0, size(orderedAttractions) - 1) as index
UNWIND shortestRouteNodeIds[index] as shortestViaNodeId
WITH orderedAttractions, totalCost, index,
    CASE WHEN shortestRouteNodeIds[index][0] = ids[index]
    THEN tail(collect(shortestViaNodeId))
        ELSE tail(reverse(collect(shortestViaNodeId)))
        END as orderedViaNodeIds
  ORDER BY index
UNWIND orderedViaNodeIds as orderedViaNodeId
MATCH (c:Attraction) where id(c) = orderedViaNodeId
RETURN [c in orderedAttractions | c.name] as names,
       [c in orderedAttractions | c.attractionId] as attractionIds,
       [orderedAttractions[0].attractionId] + collect(c.attractionId) as attractionRoute, totalCost