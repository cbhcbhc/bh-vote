package com.bhvote.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.bhvote.auth", "com.bhvote.redis"})
@MapperScan(basePackages = "com.bhvote.auth.mapper")
@EnableFeignClients(basePackages = "com.bhvote.auth.feign")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }
}
