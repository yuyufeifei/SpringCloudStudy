package com.gzh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

/**
 * @author GZH
 * @date 2021-12-15
 * 注解@LoadBalancerClient在启动时加载自定义的负载均衡算法，自定义的类不要放在主启动类同级包下
 */
@SpringBootApplication
//@LoadBalancerClient(name = "服务名", configuration = 自定义类.class)
public class DeptConsumer8212 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer8212.class, args);
    }
}
