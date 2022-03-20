package com.gzh.springcloud.service;

import com.gzh.springcloud.entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author GZH
 * @date 2021-12-18
 */
@Component
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

    @GetMapping("dept")
    List<Dept> getDeptList();

    @GetMapping("dept/{id}")
    Dept getDeptById(@PathVariable("id") int id);

}
