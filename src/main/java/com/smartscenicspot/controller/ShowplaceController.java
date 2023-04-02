package com.smartscenicspot.controller;

import com.smartscenicspot.dto.ShowplaceDto;
import com.smartscenicspot.service.ShowplaceService;
import com.smartscenicspot.vo.Result;
import com.smartscenicspot.vo.ShowplaceVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 景区管理控制类
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:57
 **/

@RestController
@RequestMapping("/api/showplace")
public class ShowplaceController {

    @Resource
    ShowplaceService showplaceService;

    @GetMapping(value = "/fuzzyposition")
    public Result<?> matchFuzzyPosition(@RequestParam(name = "lat") Double lat,
                                        @RequestParam(name = "lng") Double lng) {
        ShowplaceVo showplaceVo = showplaceService.matchFuzzyPosition(lat, lng);
        if(showplaceVo == null) {
            List<ShowplaceVo> showplaceVoList = showplaceService.getAllShowplaces();
            return Result.success(showplaceVoList);
        }
        return Result.success(showplaceVo);
    }

    @GetMapping(value = "/{id}")
    public Result<?> getShowplaceById(@PathVariable(name = "id") Long id) {
        ShowplaceDto showplaceDTO = showplaceService.getDTOById(id);
        return Result.success(showplaceDTO);
    }

}
