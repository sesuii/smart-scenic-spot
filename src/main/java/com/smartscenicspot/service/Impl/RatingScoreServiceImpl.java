package com.smartscenicspot.service.Impl;

import com.smartscenicspot.db.pgql.entity.RatingScore;
import com.smartscenicspot.db.pgql.entity.User;
import com.smartscenicspot.db.pgql.repository.RatingScoreRepository;
import com.smartscenicspot.db.pgql.repository.UserRepository;
import com.smartscenicspot.dto.RatingScoreDto;
import com.smartscenicspot.mapper.RatingScoreMapper;
import com.smartscenicspot.service.RatingScoreService;
import com.smartscenicspot.vo.DailyHotVo;
import com.smartscenicspot.vo.PageVo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    private final UserRepository userRepository;

    public RatingScoreServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean addNewRatingScore(RatingScoreDto ratingScoreDto) {
        String openid = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByOpenid(openid).orElse(null);
        if(user == null) {
            return false;
        }
        ratingScoreDto.setUserId(user.getId());
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


    @Override
    @Transactional(value = "pgqlTransactionManger", rollbackFor = Exception.class)
    public DailyHotVo getHotDataByTimeSlot(String st, String et) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime, endTime;
        try {
            startTime = format.parse(st);
            endTime = format.parse(et);
        } catch (ParseException e) {
            startTime = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000L);
            endTime = new Date();
        }
        DailyHotVo dailyHotVo = DailyHotVo.builder()
                .startTime(startTime)
                .endTime(endTime)
                .dailyVisitors(ratingScoreRepository.countByGmtModifiedBetween(startTime, endTime))
                .build();
        List<Map<String, Object>> queryResult = ratingScoreRepository.groupCountByAttraction(startTime, endTime);
        dailyHotVo.setTopAttraction(queryResult.stream().collect(Collectors.toMap(
                map -> (String) map.get("name"),
                map -> (Long) map.get("cnt")
                )));
        return dailyHotVo;
    }

}
