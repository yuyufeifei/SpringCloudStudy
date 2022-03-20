package com.gzh.springcloud.dao;

import com.gzh.springcloud.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GZH
 * @date 2021-12-15
 */
@Mapper
@Repository
public interface DeptDao {

    List<Dept> getDeptList();

    Dept getDeptById(int id);

}
