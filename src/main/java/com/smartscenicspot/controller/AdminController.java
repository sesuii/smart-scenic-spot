package com.smartscenicspot.controller;

import com.smartscenicspot.dto.AdminDto;
import com.smartscenicspot.dto.AttractionUpdateDto;
import com.smartscenicspot.dto.NoticeDto;
import com.smartscenicspot.service.AdminService;
import com.smartscenicspot.service.AttractionService;
import com.smartscenicspot.service.NoticeService;
import com.smartscenicspot.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 景区管理模块
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:54
 **/

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    AdminService adminService;

    @Resource
    AttractionService attractionService;

    @Resource
    NoticeService noticeService;

    @GetMapping("/info")
    public Result<?> getAdminInfo() {
        AdminDto adminDto = adminService.getAdminInfo();
        return Result.success(adminDto);
    }
    @PutMapping("/attraction-update/{id}")
    public Result<?> updateAttraction(@PathVariable("id") Long id
            , @RequestBody AttractionUpdateDto updateDto) {
        boolean updated = attractionService.updateInfo(id, updateDto);
        return Result.success(updated);
    }

    @PostMapping("/publish")
    public Result<?> publishNotice(@RequestBody NoticeDto noticeDto) {
        noticeService.broadCast(noticeDto);
        return Result.success();
    }

    @PutMapping("/attraction-switch/{id}")
    public Result<?> changeStatusOfAttraction(@PathVariable Long attractionId) {
        boolean closed = attractionService.changeStatus(attractionId);
        return Result.success(closed);
    }


}
