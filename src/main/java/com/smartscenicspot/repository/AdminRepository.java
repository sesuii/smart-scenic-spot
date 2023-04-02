package com.smartscenicspot.repository;

import com.smartscenicspot.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 21:07
 **/
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findAdminByAccount(String account);
}
