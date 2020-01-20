package com.atdyl.redission.controller.config;

import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author Dong YL
 * @version V1.0 2020/1/2 10:02
 */
@Configuration
public class RedissonConfig {

    /**
     * 直接注入RedissonClient就可以直接使用.
     */
    @Resource
    private RedissonClient redissonClient;



}
