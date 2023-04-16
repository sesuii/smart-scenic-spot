package com.smartscenicspot.service.Impl;

import com.smartscenicspot.dto.AttractionQueryDto;
import com.smartscenicspot.dto.AttractionUpdateDto;
import com.smartscenicspot.dto.StaffDto;
import com.smartscenicspot.mapper.AttractionMapper;
import com.smartscenicspot.mapper.StaffMapper;
import com.smartscenicspot.db.pgql.entity.Attraction;
import com.smartscenicspot.db.pgql.repository.AttractionRepository;
import com.smartscenicspot.db.pgql.repository.StaffRepository;
import com.smartscenicspot.service.AttractionService;
import com.smartscenicspot.service.Neo4jService;
import com.smartscenicspot.vo.AttractionVo;
import com.smartscenicspot.vo.PageVo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:51
 **/

@Service
public class AttractionServiceImpl implements AttractionService {

    @Resource
    AttractionRepository attractionRepository;

    @Resource
    StaffRepository staffRepository;

    @Resource
    Neo4jService neo4jService;

    @Override
    public AttractionVo getVoById(Long id) {
        Attraction attraction = attractionRepository.findById(id).orElse(null);
        if(attraction == null) {
            return null;
        }
        return AttractionMapper.INSTANCE.toVo(attraction);
    }

    @Override
    public boolean addNewAttraction(AttractionVo attractionVo) {
        Attraction attraction = AttractionMapper.INSTANCE.voToEntity(attractionVo);
        attractionRepository.save(attraction);
        return true;
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

    @Override
    @Transactional(value = "pgqlTransactionManger")
    public PageVo<?> getAllVos(AttractionQueryDto attractionQueryDto) {
        Attraction attraction = AttractionMapper.INSTANCE.queryDtoToEntity(attractionQueryDto);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Page<Attraction> attractions = attractionRepository.findAll(Example.of(attraction, exampleMatcher),
                PageRequest.of(attractionQueryDto.getCurrentPage() - 1
                        , attractionQueryDto.getPageSize()));
        return PageVo.builder()
                .data(Collections.singletonList(AttractionMapper.INSTANCE
                        .toVoList(attractions.getContent())))
                .totalElements(attractions.getTotalElements())
                .totalPages(attractions.getTotalPages())
                .build();
    }

    @Override
    public List<StaffDto> getStaffs(Long id) {
        return StaffMapper.INSTANCE.toDtoList(staffRepository.findAllByAttractionId(id));
    }

    @Override
    @Transactional(value = "pgqlTransactionManger", rollbackFor = Exception.class)
    public boolean changeStatus(Long attractionId) {
        Attraction attraction = attractionRepository.findById(attractionId).orElse(null);
        if(attraction == null) {
            return false;
        }
        attractionRepository.updateStatusById(attraction.getStatus() == (byte) 1
                ? (byte) 0 : (byte) 1, attractionId);
        return neo4jService.changeStatus(attractionId, (int) attraction.getStatus());
    }

}
