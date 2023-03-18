package com.smartscenicspot.service.Impl;

import com.smartscenicspot.constant.RedisConstant;
import com.smartscenicspot.constant.SecurityConstant;
import com.smartscenicspot.domain.User;
import com.smartscenicspot.repository.UserRepository;
import com.smartscenicspot.service.UserService;
import com.smartscenicspot.utils.JwtUtil;
import com.smartscenicspot.utils.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/15 16:08
 **/

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    UserRepository userRepository;

    public User getByOpenid(String openid) {
        return userRepository.getUserByOpenid(openid);
    }

    /**
     * 从 Security 中拿出经过 WeChatAuthenticationProvider 验证的 openid
     * 生成 Token 存入 Redis 并返回给客户端
     *
     * @return token
     */
    public Map<String, String> toWeChatLogin(String code) {
        // ====测试用户登录，不获取openid========
        if(code.startsWith("test_")) {
            User user = getByOpenid(code);
            String token = JwtUtil.createJWT(code);
            redisTemplate.opsForValue().set(RedisConstant.USER_PREFIX + code,
                    token, RedisConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
            return Map.of(SecurityConstant.SECURITY_HEADER,
                    SecurityConstant.SECURITY_HEADER_PREFIX + token);
        }
        // ==================================

        String openid = WeChatUtil.jscode2session(code);
        if(openid == null) {
            return null;
        }
        User user = getByOpenid(openid);
        if(user == null) {
            createUser(openid);
        }
        String token = JwtUtil.createJWT(openid);
        redisTemplate.opsForValue().set(RedisConstant.USER_PREFIX + openid,
                token, RedisConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        return Map.of(SecurityConstant.SECURITY_HEADER,
                SecurityConstant.SECURITY_HEADER_PREFIX + token);
    }

    public void createUser(String openid) {

    }
}
