package com.smartscenicspot.service;

import com.smartscenicspot.dto.AttractionQueryDto;
import com.smartscenicspot.dto.AttractionUpdateDto;
import com.smartscenicspot.dto.StaffDto;
import com.smartscenicspot.vo.AttractionVo;
import com.smartscenicspot.vo.PageVo;

import java.util.List;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:48
 **/
public interface AttractionService {
    AttractionVo getVoById(Long id);

    boolean addNewAttraction(AttractionVo attractionVo);


    boolean updateInfo(Long id, AttractionUpdateDto updateDto);

    PageVo<?> getAllVos(AttractionQueryDto attractionQueryDto);

    List<StaffDto> getStaffs(Long id);

    boolean changeStatus(Long attractionId);
}
