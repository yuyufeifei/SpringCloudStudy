package com.gzh.springcloud.controller;

import com.gzh.springcloud.entity.Dept;
import com.gzh.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author GZH
 * @date 2021-12-15
 */
@RestController
public class DeptController {

    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @GetMapping("dept")
    public List<Dept> getDeptList() {
        List<Dept> deptList = deptService.getDeptList();
        System.out.println("8211" + deptList);
        return deptList;
    }

    /**
     * 注解@HystrixCommand用于服务熔断
     */
    @GetMapping("dept/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGetDeptById")
    public Dept getDeptById(@PathVariable Integer id) {
        Dept dept = deptService.getDeptById(id);
        System.out.println("8211" + dept);
        if (dept == null) {
            throw new RuntimeException("id:" + id + "不存在或无法找到");
        }
        return dept;
    }
    public Dept hystrixGetDeptById(@PathVariable Integer id) {
        Dept dept = new Dept();
        dept.setDeptno(id);
        dept.setDname("id:" + id + "不存在或无法找到--@Hystrix");
        dept.setDb_source("no this database in MySQL");
        return dept;
    }

}
