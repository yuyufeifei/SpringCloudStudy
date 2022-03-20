package com.gzh.springcloud.service.impl;

import com.gzh.springcloud.dao.DeptDao;
import com.gzh.springcloud.entity.Dept;
import com.gzh.springcloud.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GZH
 * @date 2021-12-15
 */
@Service
public class DeptServiceImpl implements DeptService {

    private final DeptDao deptDao;

    public DeptServiceImpl(DeptDao deptDao) {
        this.deptDao = deptDao;
    }

    @Override
    public List<Dept> getDeptList() {
        return deptDao.getDeptList();
    }
}
