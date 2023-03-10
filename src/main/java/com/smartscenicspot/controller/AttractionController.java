package com.smartscenicspot.controller;

import com.smartscenicspot.constant.ResultEnum;
import com.smartscenicspot.dto.AttractionDto;
import com.smartscenicspot.dto.AttractionUpdateDto;
import com.smartscenicspot.service.AttractionService;
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
        AttractionDto attractionDTO = attractionService.getDtoById(id);
        if(attractionDTO == null) {
            return Result.success(ResultEnum.NOT_FOUND);
        }
        return Result.success(attractionDTO);
    }

    @GetMapping("/all")
    public Result<?> getAttractions(@RequestParam Integer pageSize, @RequestParam Integer currentPage) {
        PageVo<?> pageVo = attractionService
                .getAllDtos(currentPage - 1, pageSize);
        return Result.success(pageVo);
    }

    @PutMapping("/{id}/update")
    public Result<?> updateAttraction(@PathVariable("id") Long id
            , @RequestBody AttractionUpdateDto updateDto) {
        boolean updated = attractionService.updateInfo(id, updateDto);
        return Result.success(updated);
    }
}
