package com.smartscenicspot.db.pgql.repository;

import com.smartscenicspot.db.pgql.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 21:03
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByOpenid(String openid);

    boolean existsUserByOpenid(String openid);
}
