package com.wxw;

import com.wxw.domain.Employee;
import com.wxw.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: wxw
 * @create: 2020-03-27-12:28
 */
@SpringBootTest
public class TestPlus {

    @Autowired
    private EmployeeMapper employeeMapper;

    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Employee> userList = employeeMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}
