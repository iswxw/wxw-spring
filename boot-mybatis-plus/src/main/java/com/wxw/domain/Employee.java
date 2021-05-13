package com.wxw.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * employee
 * @author 
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工编号
     */
    private Long employeeId;

    /**
     * 员工名称
     */
    private String employeeName;

    /**
     * 员工邮箱
     */
    private String employeeEmail;

    /**
     * 备注信息
     */
    private String employeeRemark;
}