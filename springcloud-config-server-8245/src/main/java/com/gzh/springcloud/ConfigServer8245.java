package com.gzh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author GZH
 * @date 2021-12-21
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServer8245 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer8245.class, args);
    }
}
