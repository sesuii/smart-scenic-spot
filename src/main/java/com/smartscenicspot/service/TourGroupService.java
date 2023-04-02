package com.smartscenicspot.service;

import com.smartscenicspot.dto.TourGroupDto;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.TourGroupVo;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:49
 **/
public interface TourGroupService {
    boolean joinGroup(String inviteCode);

    TourGroupVo getVoById(Long id);

    PageVo<?> getAllDtos(int page, int size);

    TourGroupDto createNewGroup(TourGroupDto tourGroupDto);
}
