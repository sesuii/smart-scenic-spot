package com.smartscenicspot.controller;

import com.smartscenicspot.constant.ResultEnum;
import com.smartscenicspot.dto.AttractionQueryDto;
import com.smartscenicspot.dto.StaffDto;
import com.smartscenicspot.service.AttractionService;
import com.smartscenicspot.vo.AttractionVo;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @PostMapping("/all")
    public Result<?> getAttractions(@RequestBody AttractionQueryDto attractionQueryDto){
        PageVo<?> pageVo = attractionService.getAllVos(attractionQueryDto);
        return Result.success(pageVo);
    }

    @GetMapping("/{id}/staffs")
    public Result<?> getStaffsByAttractionId(@PathVariable("id") Long attractionId) {
        List<StaffDto> staffDtos = attractionService.getStaffs(attractionId);
        return Result.success(staffDtos);
    }
}
