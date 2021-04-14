package com.zsy.product.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author: zhangshuaiyin
 * @create: 2020-06-11 09:39
 **/

@Configuration
public class MyRedissonConfig {

    /**
     * 所有对 Redisson 的使用都是通过 RedissonClient
     *
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        // 1、创建配置
        Config config = new Config();
        // Redis url should start with redis:// or rediss://
        config.useSingleServer().setAddress("redis://192.168.163.131:6379");

        // 2、根据 Config 创建出 RedissonClient 实例
        return Redisson.create(config);
    }

}
