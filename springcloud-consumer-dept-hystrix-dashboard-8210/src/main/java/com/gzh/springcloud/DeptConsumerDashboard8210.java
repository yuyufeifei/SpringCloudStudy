package com.gzh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author GZH
 * @date 2021-12-20
 */
@SpringBootApplication
@EnableHystrixDashboard
public class DeptConsumerDashboard8210 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerDashboard8210.class, args);
    }
}
