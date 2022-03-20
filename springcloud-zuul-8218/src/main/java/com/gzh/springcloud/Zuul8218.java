package com.gzh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author GZH
 * @date 2021-12-20
 */
@SpringBootApplication
@EnableZuulProxy
public class Zuul8218 {
    public static void main(String[] args) {
        SpringApplication.run(Zuul8218.class, args);
    }
}
