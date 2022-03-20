package com.gzh.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

/**
 * @author GZH
 * @date 2021-12-15
 * 注解@EnableCircuitBreaker已弃用，改用@EnableHystrix
 * 注解@EnableHystrix为使用Hystrix实现服务熔断
 */
@SpringBootApplication
@EnableHystrix
public class HystrixDeptProvider8211 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDeptProvider8211.class, args);
    }
    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> hystrixMetricsStreamServlet() {
        return new ServletRegistrationBean<>(new HystrixMetricsStreamServlet(), "/actuator/hystrix.stream");
    }
}
