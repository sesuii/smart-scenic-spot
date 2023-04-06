package com.smartscenicspot.service.Impl;

import com.smartscenicspot.dto.RatingScoreDto;
import com.smartscenicspot.mapper.RatingScoreMapper;
import com.smartscenicspot.db.pgql.pojo.RatingScore;
import com.smartscenicspot.db.pgql.repository.RatingScoreRepository;
import com.smartscenicspot.service.RatingScoreService;
import com.smartscenicspot.vo.PageVo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * 游客评分业务实现
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/6 16:25
 **/

@Service
public class RatingScoreServiceImpl implements RatingScoreService {

    @Resource
    RatingScoreRepository ratingScoreRepository;


    @Override
    public boolean addNewRatingScore(RatingScoreDto ratingScoreDto) {
        RatingScore ratingScore = ratingScoreRepository.findByUser_IdAndAttraction_Id(
                ratingScoreDto.getUserId(), ratingScoreDto.getAttractionId()
        ).orElse(null);
        if(ratingScore == null) {
            ratingScoreRepository.save(RatingScoreMapper.INSTANCE.toEntity(ratingScoreDto));
        }
        else {
            RatingScoreMapper.INSTANCE.partialUpdate(ratingScoreDto, ratingScore);
            ratingScoreRepository.save(ratingScore);
        }
        return true;
    }

    @Override
    public RatingScoreDto getDtoById(Long id) {
        RatingScore ratingScore = ratingScoreRepository.findById(id).orElse(null);
        if(ratingScore == null) {
            return null;
        }
        return RatingScoreMapper.INSTANCE.toDto(ratingScore);
    }

    @Override
    public PageVo<?> getAllDtos(RatingScoreDto ratingScoreDto) {
        RatingScore ratingScore = RatingScoreMapper.INSTANCE.toEntity(ratingScoreDto);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("comment", ExampleMatcher.GenericPropertyMatchers.contains());
        Page<RatingScore> ratingScores = ratingScoreRepository.findAll(Example.of(ratingScore, exampleMatcher),
                PageRequest.of(ratingScoreDto.getCurrentPage() - 1, ratingScoreDto.getPageSize()));
        return PageVo.builder()
                .data(Collections.singletonList(RatingScoreMapper.INSTANCE
                        .toDtos(ratingScores.getContent())))
                .totalElements(ratingScores.getTotalElements())
                .totalPages(ratingScores.getTotalPages())
                .build();
    }

    @Override
    public boolean deleteRatingScore(Long id) {
        ratingScoreRepository.deleteById(id);
        return true;
    }
}
