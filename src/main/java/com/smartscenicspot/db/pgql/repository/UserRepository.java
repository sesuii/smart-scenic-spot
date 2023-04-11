package com.smartscenicspot.db.pgql.repository;

import com.smartscenicspot.db.pgql.entity.TourGroup;
import com.smartscenicspot.db.pgql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 21:03
 **/
@Repository
@Transactional(transactionManager = "pgqlTransactionManger")
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query("update User u set u.tourGroup = ?1 where u.openid = ?2")
    int updateTourGroupByOpenid(TourGroup tourGroup, String openid);


    Optional<User> findByOpenid(String openid);

    boolean existsUserByOpenid(String openid);
}
