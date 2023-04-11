package com.smartscenicspot.db.pgql.repository;

import com.smartscenicspot.db.pgql.entity.InterestTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "pgqlTransactionManger", rollbackFor = Exception.class)
public interface InterestTagRepository extends JpaRepository<InterestTag, Long> {
}