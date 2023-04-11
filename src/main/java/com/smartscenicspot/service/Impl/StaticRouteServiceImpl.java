package com.smartscenicspot.service.Impl;

import com.smartscenicspot.db.pgql.entity.Route;
import com.smartscenicspot.db.pgql.repository.RouteRepository;
import com.smartscenicspot.dto.StaticRouteDto;
import com.smartscenicspot.mapper.RouteMapper;
import com.smartscenicspot.service.StaticRouteService;
import com.smartscenicspot.vo.PageVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * 静态路线业务逻辑实现
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/9 3:31
 **/
@Service
public class StaticRouteServiceImpl implements StaticRouteService {

    @Resource
    RouteRepository routeRepository;

    @Override
    public PageVo<?> getAllStaticRoutes(int page, int size) {
        Page<Route> routes = routeRepository.findAll(PageRequest.of(page, size, Sort.by(
                Sort.Order.desc("gmtModified")
        )));
        return PageVo.builder()
                .data(Collections.singletonList(RouteMapper.INSTANCE.toDtoList(routes.getContent())))
                .totalPages(routes.getTotalPages())
                .totalElements(routes.getTotalElements())
                .build();
    }

    @Override
    public StaticRouteDto getDtoById(Long id) {
        Route route = routeRepository.findById(id).orElse(null);
        return RouteMapper.INSTANCE.toDto(route);
    }
}
