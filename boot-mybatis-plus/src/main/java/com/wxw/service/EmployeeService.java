package com.wxw.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wxw.dao.EmployeeDao;
import com.wxw.domain.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/5/14
 */
@Service
// @DS("bootcache") 默认数据源
public class EmployeeService {

    @Resource
    private EmployeeDao employeeDao;


    @DS("multisource")
    public List<Employee> selectList() {
        return employeeDao.selectList();
    }


    @DS("multisource")
    public void addEmployeeBySelective(Employee employee) {
        employeeDao.insertSelective(employee);
    }
}
