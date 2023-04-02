package com.smartscenicspot.repository;

import com.smartscenicspot.pojo.TourGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TourGroupRepository extends JpaRepository<TourGroup, Long> {
    @Transactional
    @Modifying
    @Query("update TourGroup t set t.groupSize = ?1")
    int updateGroupSizeBy(Integer groupSize);

    Optional<TourGroup> findByInviteCode(String inviteCode);
}