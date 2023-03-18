package com.smartscenicspot.service.Impl;

import com.smartscenicspot.domain.Attraction;
import com.smartscenicspot.domain.Showplace;
import com.smartscenicspot.dto.AttractionDto;
import com.smartscenicspot.repository.AttractionRepository;
import com.smartscenicspot.repository.ShowplaceRepository;
import com.smartscenicspot.service.AttractionService;
import com.smartscenicspot.vo.AttractionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:51
 **/

@Service
public class AttractionServiceImpl implements AttractionService {

    @Resource
    AttractionRepository attractionRepository;

    @Resource
    ShowplaceRepository showplaceRepository;

    @Override
    public AttractionDto getDTOById(Long id) {
        Optional<Attraction> optionalAttraction = attractionRepository.findById(id);
        if(optionalAttraction.isEmpty()) {
            return null;
        }
        Attraction attraction = optionalAttraction.get();
        AttractionDto attractionDTO = new AttractionDto();
        BeanUtils.copyProperties(attraction, attractionDTO);
        return attractionDTO;
    }

    @Override
    public boolean addNewAttraction(AttractionVo attractionVo) {
        Attraction attraction = new Attraction();
        BeanUtils.copyProperties(attractionVo, attraction);
        Showplace showplace = showplaceRepository
                .findById(attractionVo.getParentId()).orElse(null);
        attraction.setShowplace(showplace);
        attractionRepository.save(attraction);
        return true;
    }

    @Override
    public List<AttractionDto> convertToAttractionList(List<Attraction> attractions) {
        return attractions.stream()
                .map(this::convertToAttractionDto)
                .collect(Collectors.toList());
    }

    private AttractionDto convertToAttractionDto(Attraction attraction) {
        AttractionDto attractionDto = new AttractionDto();
        BeanUtils.copyProperties(attraction, attractionDto);
        return attractionDto;
    }
}
