package com.smartscenicspot.utils;

import com.smartscenicspot.constant.SecurityConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 生成和解析 jwt
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/5 13:16
 **/
public class JwtUtil {

    /**
     *生成 JWT，使用 HS256 算法
     *
     * @param subject 待加密信息
     * @return Token
     *
     */
    public static String createJWT(String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Map<String, Object> claims = new HashMap<>(16);
        claims.put("token", subject);
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setSubject(subject)
                .setExpiration(new Date(nowMillis + SecurityConstant.TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, SecurityConstant.SECRET_KEY);
        return builder.compact();
    }

    /**
     * 解密 Token
     *
     * @param token 
     * @return
     *
     */
    public static Claims parseJWT(String token) {
        return Jwts.parser()
                .setSigningKey(SecurityConstant.SECRET_KEY)
                .parseClaimsJws(token).getBody();
    }
}
