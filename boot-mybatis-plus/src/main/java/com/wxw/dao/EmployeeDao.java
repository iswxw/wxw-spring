package com.wxw.dao;

import com.wxw.domain.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeDao {
    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectList();
}