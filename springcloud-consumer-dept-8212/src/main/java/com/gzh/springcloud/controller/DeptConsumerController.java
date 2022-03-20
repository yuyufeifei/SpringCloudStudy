package com.gzh.springcloud.controller;

import com.gzh.springcloud.entity.Dept;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author GZH
 * @date 2021-12-15
 */
@RestController
public class DeptConsumerController {

    private final RestTemplate restTemplate;

    public DeptConsumerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    public static final String REST_URL_PREFIX = "http://localhost:8211";
    //Ribbon 通过服务名访问
    public static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";

    @GetMapping("/consumer/dept")
    public Object getDeptList() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept", List.class);
    }

    @GetMapping("/consumer/dept/{id}")
    public Object getDeptById(@PathVariable Integer id) {
        return restTemplate.getForEntity(REST_URL_PREFIX + "/dept/" + id, Dept.class);
    }

}
