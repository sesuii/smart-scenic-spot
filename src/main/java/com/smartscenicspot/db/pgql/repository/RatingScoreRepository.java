package com.smartscenicspot.db.pgql.repository;

import com.smartscenicspot.db.pgql.pojo.RatingScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RatingScoreRepository extends JpaRepository<RatingScore, Long> {


    @Query("select r from RatingScore r where r.user.id = ?1 and r.attraction.id = ?2")
    Optional<RatingScore> findByUser_IdAndAttraction_Id(Long userId, Long attractionId);
}