package com.smartscenicspot.constant;

/**
 * SpringSecurity 相关常量描述
 *
 * @author <a href="mailto: sjiahui@gmail.com">songjiahui</a>
 * @since 2023/3/5 13:15
 **/
public class SecurityConstant {

    /**
     * Token 存活时间 6h
     */
    public static final int TOKEN_EXPIRE_TIME = 1000 * 3600 * 6;

    /**
     * 加密 Token 固定密钥
     */
    public static final String SECRET_KEY = "SMART_SCENIC_SPOT";

}
