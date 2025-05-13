package com.chenjiajin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author com.chenjiajin
 */
@SpringBootApplication
@MapperScan("com.chenjiajin.mapper")
@EnableFeignClients
public class SonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SonApplication.class, args);
    }


}
