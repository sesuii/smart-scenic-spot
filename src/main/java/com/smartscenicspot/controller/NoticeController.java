package com.smartscenicspot.controller;

import com.smartscenicspot.dto.NoticeDto;
import com.smartscenicspot.service.NoticeService;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * /notice
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:57
 **/

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    NoticeService noticeService;

    @GetMapping("/{id}")
    public Result<?> getNoticeById(@PathVariable(name = "id") Long id) {
        NoticeDto noticeDto = noticeService.getDtoById(id);
        return Result.success(noticeDto);
    }

    @GetMapping("/public")
    public Result<?> getNoticeList(@RequestParam Integer pageSize, @RequestParam Integer currentPage) {
        PageVo<?> pageVo = noticeService
                .getAllDtos(currentPage - 1, pageSize);
        return Result.success(pageVo);
    }

    @GetMapping("/group")
    public Result<?> getGroupNotices(@RequestParam Integer pageSize, @RequestParam Integer currentPage) {
        PageVo<?> pageVo = noticeService.getGroupNoticeDtos(currentPage - 1, pageSize);
        return Result.success(pageVo);
    }

    @PostMapping("/group-publish")
    public Result<?> publishGroupNotice(@RequestBody NoticeDto noticeDto) {
        noticeService.publishGroupNotice(noticeDto);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> deleteGroupNotice(@PathVariable Long id) {
        boolean deleted = noticeService.deleteGroupNotice(id);
        return Result.success(deleted);
    }

}
