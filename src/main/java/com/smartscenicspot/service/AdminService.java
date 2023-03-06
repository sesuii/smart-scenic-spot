package com.smartscenicspot.service;

import com.smartscenicspot.domain.Admin;
import com.smartscenicspot.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 21:00
 **/
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin getOneByAccount(String account) {
        return adminRepository.getAdminByAccount(account);
    }
}
