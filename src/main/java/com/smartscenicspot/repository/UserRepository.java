package com.smartscenicspot.repository;

import com.smartscenicspot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto: sjiahui@gmail.com">songjiahui</a>
 * @since 2023/3/6 21:03
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByOpenid(String openid);
}
