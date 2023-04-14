package com.smartscenicspot.controller;

import com.smartscenicspot.constant.ResultEnum;
import com.smartscenicspot.dto.StaticRouteDto;
import com.smartscenicspot.service.Neo4jService;
import com.smartscenicspot.service.StaticRouteService;
import com.smartscenicspot.vo.BestRouteResultVo;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.Result;
import com.smartscenicspot.vo.RouteQueryVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * /route
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/9 3:33
 **/
@RestController
@RequestMapping("/route")
public class RouteController {

    @Resource
    StaticRouteService staticRouteService;

    @Resource
    Neo4jService neo4jService;

    @GetMapping("/static/{id}")
    public Result<?> getStaticRouteById(@PathVariable(name = "id") Long id) {
        StaticRouteDto staticRouteDto = staticRouteService.getDtoById(id);
        if(staticRouteDto == null) {
            return Result.success(ResultEnum.NOT_FOUND);
        }
        return Result.success(staticRouteDto);
    }

    @GetMapping("/static/all")
    public Result<?> getStaticRoutes(@RequestParam Integer pageSize,
                                     @RequestParam Integer currentPage){
        PageVo<?> pageVo = staticRouteService.getAllStaticRoutes(currentPage - 1, pageSize);
        return Result.success(pageVo);
    }

    @PostMapping("/walking/single-source")
    public Result<?> pathToSingleSource(@RequestBody RouteQueryVo routeQueryVo) {
        BestRouteResultVo route = neo4jService.getSingleSourcePath(routeQueryVo);
        return Result.success(route);
    }

    @PostMapping("/walking/multiple")
    public Result<?> realtimeHamiltonian(@RequestBody List<Long> attractionIds) {
        BestRouteResultVo route = neo4jService.getMultipleBestPath(attractionIds);
        return Result.success(route);
    }
}
