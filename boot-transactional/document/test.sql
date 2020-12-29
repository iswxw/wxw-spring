/*
 Navicat Premium Data Transfer

 Source Server         : my
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 29/12/2020 21:28:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '开发部');
INSERT INTO `dept` VALUES (2, '人事部');
INSERT INTO `dept` VALUES (3, '开发部02');
INSERT INTO `dept` VALUES (4, '测试部');
INSERT INTO `dept` VALUES (5, '开发部03');
INSERT INTO `dept` VALUES (6, '测试部02');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `emp_gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE,
  INDEX `fk_emp_dept`(`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES (1, 'ljs', 'M', 'cuteysy123@qq.com', 1);
INSERT INTO `emp` VALUES (2, 'yys', 'F', 'dd@qq.com', 1);
INSERT INTO `emp` VALUES (3, 'Jerry', 'M', '123@123.com', 3);
INSERT INTO `emp` VALUES (4, 'Tom', 'M', '122223@.com', 3);
INSERT INTO `emp` VALUES (5, '17cee0', 'M', '17cee0@123.com', 1);
INSERT INTO `emp` VALUES (6, '017a11', 'M', '017a11@123.com', 1);
INSERT INTO `emp` VALUES (7, '93eee2', 'M', '93eee2@123.com', 1);
INSERT INTO `emp` VALUES (8, '404943', 'M', '404943@123.com', 1);
INSERT INTO `emp` VALUES (9, '17ff84', 'M', '17ff84@123.com', 1);
INSERT INTO `emp` VALUES (10, '1a1625', 'M', '1a1625@123.com', 1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `score` int(20) NULL DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'aa', 60);
INSERT INTO `student` VALUES (2, 'bb', 70);
INSERT INTO `student` VALUES (3, 'cc', 80);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Jone', 18, 'test1@baomidou.com');
INSERT INTO `user` VALUES (2, 'Jack', 20, 'test2@baomidou.com');
INSERT INTO `user` VALUES (3, 'Tom', 28, 'test3@baomidou.com');
INSERT INTO `user` VALUES (4, 'Sandy', 21, 'test4@baomidou.com');
INSERT INTO `user` VALUES (5, 'Billie', 24, 'test5@baomidou.com');

SET FOREIGN_KEY_CHECKS = 1;
