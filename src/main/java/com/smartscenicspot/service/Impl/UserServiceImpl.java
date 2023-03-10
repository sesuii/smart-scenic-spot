package com.smartscenicspot.service.Impl;

import com.smartscenicspot.constant.RedisConstant;
import com.smartscenicspot.constant.SecurityConstant;
import com.smartscenicspot.domain.User;
import com.smartscenicspot.repository.UserRepository;
import com.smartscenicspot.service.UserService;
import com.smartscenicspot.utils.JwtUtil;
import com.smartscenicspot.utils.WeChatUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/15 16:08
 **/

@Service
public class UserServiceImpl implements UserService {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    UserRepository userRepository;

    /**
     * 从 Security 中拿出经过 WeChatAuthenticationProvider 验证的 openid
     * 生成 Token 存入 Redis 并返回给客户端
     *
     * @return token
     */
    public Map<String, String> toWeChatLogin(String code) {
        // ==== 测试用户登录为 test 前缀，不获取 openid ======
        String openid = code.startsWith("test_") ? code : WeChatUtil.jscode2session(code);
        if(openid == null) {
            return null;
        }
        User user = userRepository.findUserByOpenid(openid).orElse(null);
        if(user == null) {
            return null;
        }
        String token = JwtUtil.createJWT(openid);
        redisTemplate.opsForValue().set(RedisConstant.USER_PREFIX + openid,
                token, RedisConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        return Map.of(SecurityConstant.SECURITY_HEADER,
                SecurityConstant.SECURITY_HEADER_PREFIX + token);
    }

}
