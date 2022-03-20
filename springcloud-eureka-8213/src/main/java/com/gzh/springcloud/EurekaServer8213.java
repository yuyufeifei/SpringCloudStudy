package com.gzh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author GZH
 * @date 2021-12-15
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer8213 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer8213.class, args);
    }
}
