package com.wxw;

import com.wxw.domain.Employee;
import com.wxw.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wxw
 * @create: 2020-03-27-12:28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMultiDataSource {

    @Resource
    private EmployeeService employeeService;

    /**
     * 默认数据源 bootcache
     */
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Employee> userList = employeeService.selectList();
        userList.forEach(System.out::println);
    }

    /**
     * 多数据源
     */
    @Test
    public void test_Insert() {
        Employee employee = new Employee();
        employee.setEmployeeName("多数据源");
        employee.setEmployeeEmail("weixiaowei@baidu.com");
        employee.setEmployeeRemark("测试多数据源");
        employeeService.addEmployeeBySelective(employee);
    }


}
