
### 查看建表SQL
show create table employee;

### 分别在 bootcache 、multisource两个数据库中建表
CREATE TABLE `employee`
(
    `employeeId`     bigint       DEFAULT '0' COMMENT '员工编号',
    `employeeName`   varchar(50)  DEFAULT '' COMMENT '员工名称',
    `employeeEmail`  varchar(50)  DEFAULT '' COMMENT '员工邮箱',
    `employeeRemark` varchar(100) DEFAULT '' COMMENT '备注信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工表'