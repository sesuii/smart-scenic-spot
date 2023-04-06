package com.smartscenicspot.db.pgql.repository;

import com.smartscenicspot.db.pgql.pojo.Notice;
import com.smartscenicspot.db.pgql.pojo.TourGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author jiahui
 */
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Optional<Page<Notice>> findAllByTourGroup(TourGroup tourGroup, Pageable pageable);

    Optional<Page<Notice>> findAllByScope(Byte scope, Pageable pageable);
}