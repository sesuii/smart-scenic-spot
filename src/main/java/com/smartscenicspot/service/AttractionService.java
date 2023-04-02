package com.smartscenicspot.service;

import com.smartscenicspot.dto.AttractionUpdateDto;
import com.smartscenicspot.vo.AttractionVo;
import com.smartscenicspot.vo.PageVo;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:48
 **/
public interface AttractionService {
    AttractionVo getVoById(Long id);

    boolean addNewAttraction(AttractionVo attractionVo);

    PageVo<?> getAllVos(int page, int size);

    boolean updateInfo(Long id, AttractionUpdateDto updateDto);

    PageVo<?> searchDtosByName(String name, int page, int size);
}
