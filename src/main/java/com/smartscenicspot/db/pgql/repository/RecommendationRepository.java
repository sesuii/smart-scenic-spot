package com.smartscenicspot.db.pgql.repository;

import com.smartscenicspot.db.pgql.entity.Recommendation;
import com.smartscenicspot.db.pgql.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    List<Recommendation> findAllByUser(User user, Sort sort);
}