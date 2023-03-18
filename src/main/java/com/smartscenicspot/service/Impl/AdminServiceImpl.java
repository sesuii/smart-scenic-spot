package com.smartscenicspot.service.Impl;

import com.smartscenicspot.constant.RedisConstant;
import com.smartscenicspot.constant.SecurityConstant;
import com.smartscenicspot.domain.Admin;
import com.smartscenicspot.repository.AdminRepository;
import com.smartscenicspot.service.AdminService;
import com.smartscenicspot.utils.JwtUtil;
import com.smartscenicspot.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/15 15:54
 **/
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    AdminRepository adminRepository;

    public Admin getOneByAccount(String account) {
        return adminRepository.getAdminByAccount(account);
    }

    public Map<String, String> toAdminLogin(AdminVo adminVo) {
        Admin admin = getOneByAccount(adminVo.getAccount());
        if(admin == null) {
            return null;
        }
        String token = JwtUtil.createJWT(admin.getAccount());
        redisTemplate.opsForValue().set(RedisConstant.USER_PREFIX + admin.getAccount(),
                SecurityConstant.ADMIN_LABEL + token,
                RedisConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        return Map.of(SecurityConstant.SECURITY_HEADER,
                SecurityConstant.SECURITY_HEADER_PREFIX + token);
    }

    public Admin createAccount(AdminVo adminVo) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return null;
    }
}
