package com.smartscenicspot.service;

import com.smartscenicspot.domain.Attraction;
import com.smartscenicspot.dto.AttractionDto;
import com.smartscenicspot.vo.AttractionVo;

import java.util.List;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:48
 **/
public interface AttractionService {
    AttractionDto getDTOById(Long id);

    public boolean addNewAttraction(AttractionVo attractionVo);

    public List<AttractionDto> convertToAttractionList(List<Attraction> attractions);
}
