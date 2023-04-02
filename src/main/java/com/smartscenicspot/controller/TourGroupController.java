package com.smartscenicspot.controller;

import com.smartscenicspot.dto.TourGroupDto;
import com.smartscenicspot.service.TourGroupService;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.Result;
import com.smartscenicspot.vo.TourGroupVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 群组管理控制类
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:58
 **/

@RestController
@RequestMapping("/api/tourgroup")
public class TourGroupController {

    @Resource
    TourGroupService tourGroupService;

    @GetMapping("/join")
    public Result<?> joinGroup(@RequestParam String inviteCode) {
        boolean join = tourGroupService.joinGroup(inviteCode);
        return Result.success(join);
    }

    @GetMapping("/{id}")
    public Result<?> tourGroupInfo(@PathVariable Long id) {
        TourGroupVo tourGroupVo = tourGroupService.getVoById(id);
        return Result.success(tourGroupVo);
    }

    @GetMapping("/all")
    public Result<?> tourGroupsInfo(@RequestParam Integer pageSize, @RequestParam Integer currentPage) {
        PageVo<?> pageVo = tourGroupService.getAllDtos(currentPage - 1, pageSize);
        return Result.success(pageVo);
    }

    @PostMapping("/create")
    public Result<?> createGroup(@RequestBody TourGroupDto tourGroupDto) {
        TourGroupDto newGroup = tourGroupService.createNewGroup(tourGroupDto);
        return Result.success(newGroup);
    }
}
