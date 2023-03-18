package com.smartscenicspot.controller;

import com.smartscenicspot.dto.AttractionDto;
import com.smartscenicspot.service.AttractionService;
import com.smartscenicspot.vo.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultVo<?> getAtttractionById(@PathVariable(name = "id") Long id) {
        AttractionDto attractionDTO = attractionService.getDTOById(id);
        return ResultVo.success(attractionDTO);
    }


}
