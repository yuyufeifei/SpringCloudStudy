package com.gzh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author GZH
 * @date 2021-12-15
 */
@SpringBootApplication
@EnableFeignClients
public class FeignDeptConsumer8212 {
    public static void main(String[] args) {
        SpringApplication.run(FeignDeptConsumer8212.class, args);
    }
}
