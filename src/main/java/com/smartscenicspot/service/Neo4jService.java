package com.smartscenicspot.service;

import com.smartscenicspot.db.neo4j.entity.AttractionNode;
import com.smartscenicspot.vo.BestRouteResultVo;
import com.smartscenicspot.vo.RouteQueryVo;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/5 0:10
 **/
public interface Neo4jService {

    List<?> getDynamicInfo();

    BestRouteResultVo getSingleSourcePath(RouteQueryVo routeQueryVo);

    boolean changeStatus(Long attractionId, Integer status);

    boolean imitateCrowdChange(Map<String, Long> changes);

    BestRouteResultVo getMultipleBestPath(List<Long> attractionIds);

    List<AttractionNode> getOverCapacity();
}
