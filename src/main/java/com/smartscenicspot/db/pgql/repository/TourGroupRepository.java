package com.smartscenicspot.db.pgql.repository;

import com.smartscenicspot.db.pgql.entity.TourGroup;
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
    @Query("select count(t) from TourGroup t where t.status = 1")
    int activeGroupCount();
}