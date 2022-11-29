package com.zsy.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author: zhangshuaiyin
 * @date: 2021/3/7 12:07
 */
@Configuration
public class MallCorsConfiguration {
    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 配置跨域
        corsConfiguration.addAllowedHeader("*"); // 允许那些头跨域
        corsConfiguration.addAllowedMethod("*"); // 允许那些请求方法跨域
        corsConfiguration.addAllowedOrigin("*"); // 请求来源进行跨域
        corsConfiguration.setAllowCredentials(true); // 是否允许携带cookie跨域

        source.registerCorsConfiguration("/**", corsConfiguration); // 允许所有请求跨域
        return new CorsWebFilter(source);
    }
}
