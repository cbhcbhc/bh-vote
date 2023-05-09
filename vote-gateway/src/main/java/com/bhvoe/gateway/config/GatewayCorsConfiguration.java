package com.bhvoe.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Collections;

/**
 * @Description: 解决跨域的配置
 **/

@Configuration
public class GatewayCorsConfiguration {

    @Bean
    public CorsWebFilter corsFilter() {
        // 跨域配置源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //设置跨域的配置信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许所有请求来源进行跨域
        //corsConfiguration.addAllowedOrigin("*");
        // spring boot2.4以后的配置
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
        // 允许所有头进行跨域
        corsConfiguration.addAllowedHeader("*");
        // 允许所有请求方式进行跨域
        corsConfiguration.addAllowedMethod("*");
        // 允许携带cookie进行跨域
        corsConfiguration.setAllowCredentials(false);
        //任意路径都需要跨域配置
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }
}
