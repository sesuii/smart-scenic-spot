package com.smartscenicspot.service;

import com.smartscenicspot.dto.RatingScoreDto;
import com.smartscenicspot.vo.DailyHotVo;
import com.smartscenicspot.vo.PageVo;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/6 16:25
 **/
public interface RatingScoreService {

    boolean addNewRatingScore(RatingScoreDto ratingScoreDto);

    RatingScoreDto getDtoById(Long id);

    PageVo<?> getAllDtos(RatingScoreDto ratingScoreDto);

    boolean deleteRatingScore(Long id);

    DailyHotVo getHotDataByTimeSlot(String st, String et);
}
