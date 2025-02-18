package com.mty.landmg.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author york
 * @since 2025-02-18
 */
@Component
public class JwtUtil {

    private final Key key;

    /**
     * 加密字符串
     *
     * @param secret 加密字符串
     */
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 创建JWT token
     *
     * @param sub    主题，这里我们放账号
     * @param claims 载体
     * @return token
     */
    public String createToken(String sub, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(sub)
                .addClaims(claims)
                .setExpiration(Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * 解析token
     *
     * @param token token
     * @return 载体
     */
    public Map<String, Object> parseClims(String token) {
        // parseClaimsJwt() 方法是解析没有进行签名的token,
        // 签名的token应该使用parseClaimsJws(String jws) 方法
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

}
