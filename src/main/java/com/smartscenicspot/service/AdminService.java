package com.smartscenicspot.service;

import com.alibaba.fastjson.JSON;
import com.smartscenicspot.constant.RedisConstant;
import com.smartscenicspot.constant.SecurityConstant;
import com.smartscenicspot.domain.Admin;
import com.smartscenicspot.repository.AdminRepository;
import com.smartscenicspot.utils.JwtUtil;
import com.smartscenicspot.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 21:00
 **/
@Service
public class AdminService {

    @Resource
    RedisTemplate<String, String> redisTemplate;

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin getOneByAccount(String account) {
        return adminRepository.getAdminByAccount(account);
    }

    public Map<String, String> toLogin(AdminVo adminVo) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(adminVo.getUsername(), adminVo.getPassword());
        Admin admin = getOneByAccount((String) authenticationToken.getPrincipal());
        String token = JwtUtil.createJWT(admin.getAccount());
        redisTemplate.opsForValue().set(RedisConstant.USER_PREFIX + admin.getAccount(),
                JSON.toJSONString(token), RedisConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        return Map.of(SecurityConstant.SECURITY_HEADER,
                SecurityConstant.SECURITY_HEADER_PREFIX + token);
    }
}
