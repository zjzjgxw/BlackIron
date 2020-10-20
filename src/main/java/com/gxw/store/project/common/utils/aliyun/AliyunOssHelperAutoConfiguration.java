package com.gxw.store.project.common.utils.aliyun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliyunOssHelperAutoConfiguration {

    @Bean
    public AliyunOssHelper aliyunOssHelper()
    {
        return new AliyunOssHelper();
    }
}
