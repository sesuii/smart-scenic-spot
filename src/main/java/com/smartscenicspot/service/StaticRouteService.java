package com.smartscenicspot.service;

import com.smartscenicspot.dto.StaticRouteDto;
import com.smartscenicspot.vo.PageVo;

/**
 * 静态路线业务接口
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/9 3:30
 **/
public interface StaticRouteService {
    PageVo<?> getAllStaticRoutes(int page, int size);

    StaticRouteDto getDtoById(Long id);
}
