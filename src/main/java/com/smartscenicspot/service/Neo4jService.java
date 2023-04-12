package com.smartscenicspot.service;

import com.smartscenicspot.vo.BestRouteResultVo;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/5 0:10
 **/
public interface Neo4jService {

    List<?> getDynamicInfo();

    List<Long> getSingleSourcePath(Long sourceId, Long targetId);

    boolean changeStatus(Long attractionId, Integer status);

    boolean imitateCrowdChange(Map<String, String> changes);

    BestRouteResultVo getMultipleBestPath(List<Long> attractionIds);
}
