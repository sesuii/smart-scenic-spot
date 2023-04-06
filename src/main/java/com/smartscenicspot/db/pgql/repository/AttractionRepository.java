package com.smartscenicspot.db.pgql.repository;

import com.smartscenicspot.db.pgql.pojo.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jiahui
 */
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
}