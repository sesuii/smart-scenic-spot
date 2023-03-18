package com.smartscenicspot.controller;

import com.smartscenicspot.dto.ShowplaceDto;
import com.smartscenicspot.service.ShowplaceService;
import com.smartscenicspot.vo.ResultVo;
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
    public ResultVo<?> matchFuzzyPosition(@RequestParam(name = "lat") Double lat,
                                          @RequestParam(name = "lng") Double lng) {
        ShowplaceVo showplaceVo = showplaceService.matchFuzzyPosition(lat, lng);
        if(showplaceVo == null) {
            List<ShowplaceVo> showplaceVoList = showplaceService.getAllShowplaces();
            return ResultVo.success(showplaceVoList);
        }
        return ResultVo.success(showplaceVo);
    }

    @GetMapping(value = "/{id}")
    public ResultVo<?> getShowplaceById(@PathVariable(name = "id") Long id) {
        ShowplaceDto showplaceDTO = showplaceService.getDTOById(id);
        return ResultVo.success(showplaceDTO);
    }


}
