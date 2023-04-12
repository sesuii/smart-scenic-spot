package com.smartscenicspot.service;

import com.smartscenicspot.dto.ShowplaceDto;
import com.smartscenicspot.vo.ShowplaceVo;

import java.util.List;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:50
 **/
public interface ShowplaceService {
    /**
     * 根据用户的模糊经纬度找到范围 3km 内的景点
     *
     * @param lat 维度
     * @param lng 经度
     * @return showplaceVo 匹配到的景区信息
     */
    ShowplaceVo matchFuzzyPosition(double lat, double lng);

    /**
     * 获取所有景区基本信息
     *
     * @return List<ShowplaceVo> 所有景区
     */
    List<ShowplaceVo> getAllShowplaces();

    public boolean addNewShowplace(ShowplaceVo showplaceVo);

    ShowplaceDto getDTOById(Long id);
}
