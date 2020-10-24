package com.gxw.store.project.sso.service.imp;


import com.gxw.store.project.common.utils.JwtTokenUtil;
import com.gxw.store.project.common.utils.cache.RedisCache;
import com.gxw.store.project.sso.service.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtTokenServiceImp implements TokenService {
    //缓存过期时间，单位分钟
    @Value("${jwt.token.expireTime}")
    private int expireTime;

    private static final String LOGIN_CACHE_PRE = "login_user:";

    protected static final long MILLIS_MINUTE = 60 * 1000;

    private ThreadLocal<String> customPrefix = new ThreadLocal<String>();

    @Resource
    private RedisCache redisCache;

    @Override
    public HashMap<String, String> createToken(Long id, String name) {
        //放入缓存 ，放缓存不是必须的，依靠jwt就可以实现用户会话。
        //但是如果需求需要支持能够主动失效用户登陆状态，那么可以通过加缓存
        //或者有关用户的个别信息字段比较隐私，直接放在jwt中容易泄露，则也可放缓存
        setCache(id);
        String accessToken = JwtTokenUtil.createToken(id, name, expireTime);
        String refreshToken = JwtTokenUtil.createToken(id, name, expireTime + 1000);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessToken", accessToken);
        hashMap.put("refreshToken", refreshToken);
        return hashMap;
    }

    @Override
    public HashMap<String, String> createToken(Long id, String name, Long businessId) {
        setCache(id);
        String accessToken = JwtTokenUtil.createToken(id, name, businessId, expireTime);
        String refreshToken = JwtTokenUtil.createToken(id, name, businessId, expireTime + 1000);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessToken", accessToken);
        hashMap.put("refreshToken", refreshToken);
        return hashMap;
    }


    public HashMap<String, String> createToken(Long id, String name, String keyPrefix) {
        customPrefix.set(keyPrefix);
        return createToken(id, name);
    }

    @Override
    public HashMap<String, String> createToken(Long id, String name, Long businessId, String keyPrefix) {
        customPrefix.set(keyPrefix);
        return createToken(id, name, businessId);
    }


    @Override
    public void deleteToken(String token) {
        Claims claims = JwtTokenUtil.parseToken(token);
        Long id = claims.get("id", Long.class);
        redisCache.deleteObject(getRedisKey(id));
    }

    @Override
    public void deleteToken(String token, String keyPrefix) {
        customPrefix.set(keyPrefix);
        deleteToken(token);
    }

    /**
     * 依靠refreshToken 重新获取新的Token
     *
     * @param token
     * @return
     */
    @Override
    public HashMap<String, String> RefreshToken(String token) {
        Claims claims = JwtTokenUtil.parseToken(token);
        Long id = claims.get("id", Long.class);
        String name = claims.get("name", String.class);
        return createToken(id, name);
    }

    @Override
    public HashMap<String, String> RefreshToken(String token, String keyPrefix) {
        customPrefix.set(keyPrefix);
        return RefreshToken(token);
    }


    private void setCache(Long id) {
        Map<String, Object> cacheMap = new HashMap<>();
        cacheMap.put("id", id);
        cacheMap.put("expireTime", System.currentTimeMillis() + MILLIS_MINUTE * expireTime);
        redisCache.setCacheObject(getRedisKey(id), cacheMap, expireTime, TimeUnit.MINUTES);
    }

    private String getRedisKey(Long userId) {
        if (customPrefix.get() == null) {
            return LOGIN_CACHE_PRE + userId;
        } else {
            return customPrefix.get() + LOGIN_CACHE_PRE + userId;
        }
    }

}
