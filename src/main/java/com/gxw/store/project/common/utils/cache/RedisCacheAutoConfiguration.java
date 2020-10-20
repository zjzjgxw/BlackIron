package com.gxw.store.project.common.utils.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RedisConfig.class)
public class RedisCacheAutoConfiguration {

    @Bean
    public RedisCache redisCache()
    {
        return new RedisCache();
    }
}
