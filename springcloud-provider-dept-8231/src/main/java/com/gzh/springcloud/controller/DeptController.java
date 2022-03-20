package com.gzh.springcloud.controller;

import com.gzh.springcloud.entity.Dept;
import com.gzh.springcloud.service.DeptService;
import org.springframework.web.bind.annotation.GetMapping;
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
        System.out.println("8231" + deptList);
        return deptList;
    }

}
