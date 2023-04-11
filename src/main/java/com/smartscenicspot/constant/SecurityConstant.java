package com.smartscenicspot.constant;

/**
 * SpringSecurity 相关常量描述
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/5 13:15
 **/
public class SecurityConstant {

    /**
     * Token 存活时间 24h
     */
    public static final int TOKEN_EXPIRE_TIME = 1000 * 3600 * 24;

    /**
     * 加密 Token 固定密钥
     */
    public static final String JWT_SECRET_KEY = "SMART_SCENIC_SPOT";

    /**
     * Token Header
     */
    public static final String SECURITY_HEADER = "authorization";

    /**
     * Access-Token
     */
    public static final String WEBSOCKET_AUTH = "Sec-WebSocket-Protocol";

    public static final String ADMIN_LABEL = "ADMIN_";

    /**
     * Token 头部前缀
     */
    public static final String SECURITY_HEADER_PREFIX = "Bearer ";

    public static final String WECHAT_APPID = "wx28b9909abf667f2f";

    public static final String WECHAT_SECRET = "7991c862cf01f5d3cc9015280347d539";


}
