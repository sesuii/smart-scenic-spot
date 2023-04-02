package com.smartscenicspot.controller;

import com.smartscenicspot.constant.ResultEnum;
import com.smartscenicspot.service.AttractionService;
import com.smartscenicspot.vo.AttractionVo;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 景点信息管理
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:56
 **/

@RestController
@RequestMapping("/api/attraction")
public class AttractionController {

    @Resource
    AttractionService attractionService;

    @GetMapping("/{id}")
    public Result<?> getAttractionById(@PathVariable(name = "id") Long id) {
        AttractionVo attractionVo = attractionService.getVoById(id);
        if(attractionVo == null) {
            return Result.success(ResultEnum.NOT_FOUND);
        }
        return Result.success(attractionVo);
    }

    @GetMapping("/all")
    public Result<?> getAttractions(@RequestParam Integer pageSize,
                                    @RequestParam Integer currentPage) {
        PageVo<?> pageVo = attractionService
                .getAllVos(currentPage - 1, pageSize);
        return Result.success(pageVo);
    }

    @GetMapping("/search")
    public Result<?> searchByName(@RequestParam String name, @RequestParam Integer pageSize,
                                  @RequestParam Integer currentPage) {
        PageVo<?> pageVo = attractionService.searchDtosByName(name, currentPage - 1, pageSize);
        return Result.success(pageVo);
    }

}
