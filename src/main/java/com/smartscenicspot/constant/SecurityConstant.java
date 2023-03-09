package com.smartscenicspot.constant;

/**
 * SpringSecurity 相关常量描述
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
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
    public static final String JWT_SECRET_KEY = "SMART_SCENIC_SPOT";

    /**
     * Token Header
     */
    public static final String SECURITY_HEADER = "Authentication";

    /**
     * Token 头部前缀
     */
    public static final String SECURITY_HEADER_PREFIX = "Bearer ";

    public static final String WECHAT_APPID = "wx28b9909abf667f2f";

    public static final String WECHAT_SECRET = "879dbc2095dfe0452003c208f3279eb4";

    /**
     * T
     */
    public static final String SECURITY_HEADER = "Authentication";

    /**
     * Token 头部前缀
     */
    public static final String SECURITY_HEADER_PREFIX = "Bearer ";

    public static final String WECHAT_APPID = "";

    public static final String WECHAT_SECRET = "";

    public static final String WECHAT_GRANT_TYPE = "";


}
