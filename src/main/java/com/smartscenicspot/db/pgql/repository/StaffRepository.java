package com.smartscenicspot.db.pgql.repository;

import com.smartscenicspot.db.pgql.pojo.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findAllByAttractionId(Long id);

}