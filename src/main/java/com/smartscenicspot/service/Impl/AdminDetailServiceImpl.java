package com.smartscenicspot.service.Impl;

import com.smartscenicspot.pojo.Admin;
import com.smartscenicspot.repository.AdminRepository;
import com.smartscenicspot.vo.AdminVo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 实现 UserDetailService
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 20:57
 **/
@Service
public class AdminDetailServiceImpl implements UserDetailsService {

    @Resource
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findAdminByAccount(username).orElse(null);
        if(admin == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        return AdminVo.builder()
                .username(admin.getAccount())
                .password(admin.getPassword())
                .build();
    }
}
