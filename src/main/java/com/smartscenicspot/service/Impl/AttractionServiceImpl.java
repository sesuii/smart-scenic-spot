package com.smartscenicspot.service.Impl;

import com.smartscenicspot.domain.Attraction;
import com.smartscenicspot.dto.AttractionDto;
import com.smartscenicspot.dto.AttractionUpdateDto;
import com.smartscenicspot.mapper.AttractionMapper;
import com.smartscenicspot.repository.AttractionRepository;
import com.smartscenicspot.service.AttractionService;
import com.smartscenicspot.vo.AttractionVo;
import com.smartscenicspot.vo.PageVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:51
 **/

@Service
public class AttractionServiceImpl implements AttractionService {

    @Resource
    AttractionRepository attractionRepository;


    @Override
    public AttractionDto getDtoById(Long id) {
        Attraction attraction = attractionRepository.findById(id).orElse(null);
        if(attraction == null) {
            return null;
        }
        return AttractionMapper.INSTANCE.toDto(attraction);
    }

    @Override
    public boolean addNewAttraction(AttractionVo attractionVo) {
        Attraction attraction = AttractionMapper.INSTANCE.voToEntity(attractionVo);
        attractionRepository.save(attraction);
        return true;
    }

    @Override
    public PageVo<?> getAllDtos(int page, int size) {
        Page<Attraction> attractions = attractionRepository.findAll(PageRequest.of(page, size));
        return PageVo.builder()
                .data(Collections.singletonList(AttractionMapper.INSTANCE
                        .toDtoList(attractions.getContent())))
                .totalElements(attractions.getTotalElements())
                .totalPages(attractions.getTotalPages())
                .build();
    }

    @Override
    public boolean updateInfo(Long id, AttractionUpdateDto updateDto) {
        Attraction attraction = attractionRepository.findById(id).orElse(null);
        if(attraction == null) {
            return false;
        }
        Attraction updated = AttractionMapper.INSTANCE.partialUpdate(updateDto, attraction);
        attractionRepository.save(updated);
        return true;
    }

}
