package com.smartscenicspot.constant;

/**
 * Redis 相关常量
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/8 20:19
 **/
public class RedisConstant {
    /**
     * 微信用户 value 为 openid
     * 管理员用户 value 为账号
     */
    public static final String USER_PREFIX = "login_account: ";
    /**
     * Token 存活时间
     */
    public static final int TOKEN_EXPIRE_TIME = 3600 * 24;

    /**
     * websocket key
     */
    public static final String USER_OPENID = "uid";
}
