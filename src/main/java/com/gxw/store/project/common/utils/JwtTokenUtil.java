package com.gxw.store.project.common.utils;

import com.gxw.store.project.common.utils.exception.ErrorTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {
    private static final String secret = "gxwCloud";

    private static final long MILLIS_MINUTE = 60 * 1000;

    public static String createToken(Long id, String name, int expireTime) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", id);
        claims.put("name", name);
        claims.put("expireTime", System.currentTimeMillis() + MILLIS_MINUTE * expireTime);
        return createToken(claims);
    }

    public static String createToken(Long id, String name, Long businessId, int expireTime) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", id);
        claims.put("name", name);
        claims.put("businessId", businessId);
        claims.put("expireTime", System.currentTimeMillis() + MILLIS_MINUTE * expireTime);
        return createToken(claims);
    }

    public static Long getUserId(String token) {
        Claims claims = parseToken(token);
        return claims.get("id", Long.class);
    }

    public static String getUserName(String token) {
        Claims claims = parseToken(token);
        return claims.get("name", String.class);
    }

    public static Long getBusinessId(String token){
        Claims claims = parseToken(token);
        return claims.get("businessId", Long.class);
    }

    public static boolean checkExpireTime(String token){
        Claims claims = parseToken(token);
        return checkExpireTime(claims);
    }

    public static boolean checkExpireTime(Claims claims){
        long current = System.currentTimeMillis();
        if(current > claims.get("expireTime",Long.class)){
            throw new ErrorTokenException("无效token");
        }
        return true;
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public static Claims parseToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (SignatureException e) {
            throw new ErrorTokenException("无效token");
        }
    }
}
