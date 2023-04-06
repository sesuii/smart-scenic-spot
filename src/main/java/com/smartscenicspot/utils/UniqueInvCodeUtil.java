package com.smartscenicspot.utils;

import java.util.Random;

/**
 * 六位邀请码随机生成工具类
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/5 10:18
 **/
public class UniqueInvCodeUtil {
    /**
     * 字符集（去除易混淆字符）
     */
    private static final  String CHARSET = "ABCDEFGHJKMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz23456789";
    private static final int LENGTH = 6;
    private static final Random RANDOM = new Random();

    public static String generateInvCode() {
        StringBuilder code = new StringBuilder();
        for(int i = 0; i < LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARSET.length());
            code.append(CHARSET.charAt(randomIndex));
        }
        return code.toString();
    }
}
