package com.smartscenicspot.service.Impl;

import com.smartscenicspot.domain.Admin;
import com.smartscenicspot.service.AdminService;
import com.smartscenicspot.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 实现 UserDetailService
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 20:57
 **/
@Service
public class AdminDetailServiceImpl implements UserDetailsService {

    @Autowired
    AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Admin admin = adminService.getOneByAccount(account);
        if(admin == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        return AdminVo.builder()
                .account(admin.getAccount())
                .password(admin.getPassword())
                .build();
    }
}
