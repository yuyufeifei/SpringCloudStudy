package com.gzh.springcloud.service;

import com.gzh.springcloud.entity.Dept;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GZH
 * @date 2021-12-20
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

    //测试不成功

    @Override
    public DeptClientService create(Throwable cause) {
        return new DeptClientService() {
            @Override
            public List<Dept> getDeptList() {
                Dept dept = new Dept();
                dept.setDeptno(0);
                dept.setDname("客户端提供了降级的信息，这个服务现在已经被关闭");
                dept.setDb_source("无数据");
                List<Dept> deptList = new ArrayList<>();
                deptList.add(dept);
                return deptList;
            }

            @Override
            public Dept getDeptById(int id) {
                Dept dept = new Dept();
                dept.setDeptno(id);
                dept.setDname("id:" + id + "没有对应的信息。客户端提供了降级的信息，这个服务现在已经被关闭");
                dept.setDb_source("无数据");
                return dept;
            }
        };
    }
}
