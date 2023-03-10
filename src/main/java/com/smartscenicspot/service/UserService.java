package com.smartscenicspot.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 21:26
 **/
@Service
public interface UserService {

    /**
     * 从 Security 中拿出经过 WeChatAuthenticationProvider 验证的 openid
     * 生成 Token 存入 Redis 并返回给客户端
     * @param code
     * @return token
     */
    Map<String, String> toWeChatLogin(String code);

}
