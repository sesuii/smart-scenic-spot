package com.smartscenicspot.repository;

import com.smartscenicspot.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto: sjiahui@gmail.com">songjiahui</a>
 * @since 2023/3/6 21:07
 **/
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin getAdminByAccount(String account);
}
