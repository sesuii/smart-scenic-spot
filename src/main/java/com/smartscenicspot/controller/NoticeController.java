package com.smartscenicspot.controller;

import com.smartscenicspot.dto.NoticeDto;
import com.smartscenicspot.service.NoticeService;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 景区/群组公告管理
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:57
 **/

@RestController
@RequestMapping("/admin/notice")
public class NoticeController {

    @Resource
    NoticeService noticeService;

    @GetMapping("/{id}")
    public Result<?> getNoticeById(@PathVariable(name = "id") Long id) {
        NoticeDto noticeDto = noticeService.getDtoById(id);
        return Result.success(noticeDto);
    }

    @GetMapping("/all")
    public Result<?> getNoticeList(@RequestParam Integer pageSize, @RequestParam Integer currentPage) {

        PageVo<?> pageVo = noticeService
                .getAllDtos(currentPage - 1, pageSize);
        return Result.success(pageVo);
    }

    @PostMapping("/add")
    public Result<?> addNewNotice(@RequestBody NoticeDto noticeDto) {
        boolean saved = noticeService.addNewNotice(noticeDto);
        return Result.success(saved);
    }
}
