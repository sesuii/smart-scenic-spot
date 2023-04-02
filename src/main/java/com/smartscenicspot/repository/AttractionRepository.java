package com.smartscenicspot.repository;

import com.smartscenicspot.pojo.Attraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jiahui
 */
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    Page<Attraction> findByNameContaining(String name, Pageable pageable);
}