package com.smartscenicspot.db.pgql.repository;

import com.smartscenicspot.db.pgql.entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jiahui
 */
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    @Transactional
    @Modifying
    @Query("update Attraction a set a.status = ?1 where a.id = ?2")
    int updateStatusById(Byte status, Long id);
}