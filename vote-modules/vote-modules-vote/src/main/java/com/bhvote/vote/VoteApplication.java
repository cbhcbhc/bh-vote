package com.bhvote.vote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.bhvote.vote.mapper")
@EnableFeignClients(basePackages = "com.bhvote.vote.feign")
public class VoteApplication {
    public static void main(String[] args) {
        SpringApplication.run(VoteApplication.class,args);
    }
}
