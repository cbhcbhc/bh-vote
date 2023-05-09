package com.bhvote.permission;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.bhvote.permission"})
@MapperScan(basePackages = "com.bhvote.permission.mapper")
@EnableDiscoveryClient
public class PermissionApplication {
    public static void main(String[] args) {
        SpringApplication.run(PermissionApplication.class,args);
    }
}
