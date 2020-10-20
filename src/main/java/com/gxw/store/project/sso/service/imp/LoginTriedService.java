package com.gxw.store.project.sso.service.imp;

import com.gxw.store.project.common.utils.cache.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录尝试处理类
 */
@Service
public class LoginTriedService {

    @Autowired
    private RedisCache redisCache;

    private static final String LOGIN_TRIED_PRE = "login_tried:";

    private static final Long EXPIRE_SECOND = 30L; //过期时间，单位秒

    private ThreadLocal<String> customPrefix = new ThreadLocal<String>();

    /**
     * 记录尝试次数
     *
     * @param key
     */
    public void record(String key) {
        if (customPrefix.get() == null) {
            redisCache.incrementCacheObject(LOGIN_TRIED_PRE + key, 1L, EXPIRE_SECOND);
        } else {
            redisCache.incrementCacheObject(customPrefix.get() + LOGIN_TRIED_PRE + key, 1L, EXPIRE_SECOND);
        }
    }

    /**
     * 记录尝试次数
     * @param key
     * @param keyPrefix 自定义key前缀
     */
    public void record(String key, String keyPrefix) {
        customPrefix.set(keyPrefix);
        record(key);
    }

    /**
     * 获取尝试次数
     *
     * @param key
     * @return
     */
    public Integer getTriedTimes(String key) {
        if (customPrefix.get() == null) {
            return redisCache.getCacheObject(LOGIN_TRIED_PRE + key);
        } else {
            return redisCache.getCacheObject(customPrefix.get() + LOGIN_TRIED_PRE + key);
        }
    }
}
