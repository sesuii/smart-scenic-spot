package com.smartscenicspot.controller;

import com.smartscenicspot.constant.ResultEnum;
import com.smartscenicspot.dto.StaticRouteDto;
import com.smartscenicspot.service.Neo4jService;
import com.smartscenicspot.service.StaticRouteService;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 静态路线控制类
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

    @GetMapping("/walking/single-source")
    public Result<?> pathToSingleSource(@RequestParam Long sourceId, @RequestParam Long targetId) {
        List<Long> viaNodes = neo4jService.getSingleSourcePath(sourceId, targetId);
        return null;
    }

    @PostMapping("/walking/multiple")
    public Result<?> realtimeHamiltonian() {

        return null;
    }
}
