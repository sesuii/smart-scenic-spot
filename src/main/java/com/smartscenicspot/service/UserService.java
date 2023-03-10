package com.smartscenicspot.service;

import com.smartscenicspot.constant.RedisConstant;
import com.smartscenicspot.constant.SecurityConstant;
import com.smartscenicspot.domain.User;
import com.smartscenicspot.repository.UserRepository;
import com.smartscenicspot.utils.JwtUtil;
import com.smartscenicspot.utils.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 21:26
 **/
@Service
public class UserService {

    @Resource
    RedisTemplate<String, Object> redisTemplate;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        User user = User.builder()
                .openid(openid)
                .build();
        userRepository.save(user);
    }
}
