package com.smartscenicspot.repository;

import com.smartscenicspot.pojo.InterestTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterestTagRepository extends JpaRepository<InterestTag, Long> {
}