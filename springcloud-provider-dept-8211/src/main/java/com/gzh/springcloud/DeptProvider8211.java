package com.gzh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author GZH
 * @date 2021-12-15
 */
@SpringBootApplication
//@EnableEurekaClient 不配此注解也可
public class DeptProvider8211 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8211.class, args);
    }
}
