package com.smartscenicspot.service.Impl;

import com.smartscenicspot.constant.RedisConstant;
import com.smartscenicspot.constant.SecurityConstant;
import com.smartscenicspot.db.pgql.pojo.Admin;
import com.smartscenicspot.dto.AdminDto;
import com.smartscenicspot.mapper.AdminMapper;
import com.smartscenicspot.db.pgql.repository.AdminRepository;
import com.smartscenicspot.service.AdminService;
import com.smartscenicspot.utils.JwtUtil;
import com.smartscenicspot.vo.AdminVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/15 15:54
 **/
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    AdminRepository adminRepository;

    public Map<String, String> toAdminLogin(AdminVo adminVo) {
        Admin admin = adminRepository.findAdminByAccount(adminVo.getUsername()).orElse(null);
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

    public boolean createAccount(AdminDto adminDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Admin admin = AdminMapper.INSTANCE.adminDtoToAdmin(adminDto);
        admin.setPassword(encoder.encode(adminDto.getPassword()));
        adminRepository.save(admin);
        return true;
    }

    @Override
    public AdminDto getAdminInfo() {
        String account = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Admin admin = adminRepository.findAdminByAccount(account).orElse(null);
        return AdminMapper.INSTANCE.adminToAdminDto(admin);
    }
}
