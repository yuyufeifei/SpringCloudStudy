package com.gzh.springcloud.controller;

import com.gzh.springcloud.service.DeptClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author GZH
 * @date 2021-12-15
 */
@RestController
public class DeptConsumerController {

    /**
     * springcloud-api模块中的com.gzh.springcloud.service.DeptClientService
     */
    private final DeptClientService deptClientService;

    public DeptConsumerController(DeptClientService deptClientService) {
        this.deptClientService = deptClientService;
    }

    @GetMapping("/consumer/dept")
    public Object getDeptList() {
        return deptClientService.getDeptList();
    }

    @GetMapping("/consumer/dept/{id}")
    public Object getDeptById(@PathVariable Integer id) {
        return deptClientService.getDeptById(id);
    }

}
