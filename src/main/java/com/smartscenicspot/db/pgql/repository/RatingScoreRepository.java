package com.smartscenicspot.db.pgql.repository;

import com.smartscenicspot.db.pgql.entity.RatingScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RatingScoreRepository extends JpaRepository<RatingScore, Long> {


    @Query("select r from RatingScore r where r.user.id = ?1 and r.attraction.id = ?2")
    Optional<RatingScore> findByUser_IdAndAttraction_Id(Long userId, Long attractionId);

    @Query("select count(r) from RatingScore r where r.gmtModified between ?1 and ?2")
    int countByGmtModifiedBetween(Date startTime, Date endTime);
    @Query("select r.attraction.name as name, count(r) as cnt from RatingScore r " +
            "where r.gmtModified between ?1 and ?2 group by r.attraction.name " +
            "having count(r) > 0 order by count(r) desc")
    List<Map<String, Object>> groupCountByAttraction(Date startTime, Date endTime);
}