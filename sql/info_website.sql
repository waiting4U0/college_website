/*
 Navicat Premium Data Transfer

 Source Server         : con01
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : info_website

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 16/08/2020 21:33:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `message_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `message_type` int(11) NOT NULL,
  `message_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `message_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `message_addition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` datetime(0) DEFAULT NULL,
  `status` tinyint(2) DEFAULT 0 COMMENT '审核状态：0->未审核 ，1->审核通过 ，2->审核未过',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `NewIndex1`(`message_type`) USING BTREE,
  CONSTRAINT `FK_message` FOREIGN KEY (`message_type`) REFERENCES `message_type` (`type`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '学院第一次团课活动圆满举行', 2, '学院第一次团课活动圆满举行', NULL, NULL, '2019-03-28 17:59:08', NULL, 0, NULL);
INSERT INTO `message` VALUES (2, '信息学院成功举办大数据主题讲座', 2, '信息学院成功举办大数据主题讲座', NULL, NULL, '2019-03-28 17:57:26', NULL, 0, NULL);
INSERT INTO `message` VALUES (3, '信息学院党支部召开第XXX次会议', 3, '信息学院党支部召开第XXX次会议', NULL, NULL, '2019-03-28 17:59:08', NULL, NULL, NULL);
INSERT INTO `message` VALUES (61, '星期天xxxxxxxxxyyyyyyyyy', 3, '<p><img src=\"../assets/admin/pages/img/news/1561376058650.jpg\" />Next, start a free trial!</p>', NULL, NULL, '2019-06-24 19:35:27', NULL, 0, NULL);
INSERT INTO `message` VALUES (62, '', 1, '<p>Next, start a free trial!<img src=\"../assets/admin/pages/img/news/1567926442026.jpg\" width=\"112\" height=\"112\" /></p>', NULL, NULL, '2019-09-08 15:07:48', NULL, 0, NULL);

-- ----------------------------
-- Table structure for message_type
-- ----------------------------
DROP TABLE IF EXISTS `message_type`;
CREATE TABLE `message_type`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `NewIndex1`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of message_type
-- ----------------------------
INSERT INTO `message_type` VALUES (1, 1, 'notice', '2019-03-28 17:55:52', NULL);
INSERT INTO `message_type` VALUES (2, 2, 'news', '2019-03-28 17:56:16', NULL);
INSERT INTO `message_type` VALUES (3, 3, 'Party', '2019-03-28 17:56:16', NULL);
INSERT INTO `message_type` VALUES (4, 4, 'Activities ', '2019-03-28 17:56:16', NULL);

-- ----------------------------
-- Table structure for ops_function
-- ----------------------------
DROP TABLE IF EXISTS `ops_function`;
CREATE TABLE `ops_function`  (
  `id` int(11) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `resource_key` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `icon` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ops_function
-- ----------------------------
INSERT INTO `ops_function` VALUES (0, '学院首页', '/main', 'main', NULL, NULL, '2019-03-28 17:29:29', NULL);
INSERT INTO `ops_function` VALUES (1, '学院概括', '/overview', 'overview', NULL, NULL, '2019-03-28 17:29:29', NULL);
INSERT INTO `ops_function` VALUES (2, '教育教学', '/education', 'education', NULL, NULL, '2019-03-28 17:42:10', NULL);
INSERT INTO `ops_function` VALUES (3, '师资队伍', '/teachers', 'teachers', NULL, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (4, '招生就业', '/admissionEmployment', 'admissionEmployment', NULL, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (5, '党团建设', '/party', 'party', NULL, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (6, '学生工作', '/students', 'students', NULL, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (7, '联系我们', '/contactUs', 'contactUs', NULL, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (8, '学院简介', '/overview/list', 'overview', 1, NULL, '2019-03-28 17:31:16', NULL);
INSERT INTO `ops_function` VALUES (9, '学院领导', '/overview/collegeLeader', 'overview', 1, NULL, '2019-03-28 17:32:38', NULL);
INSERT INTO `ops_function` VALUES (10, '学院标识', '/overview/collegeLogo', 'overview', 1, NULL, '2019-03-28 17:32:38', NULL);
INSERT INTO `ops_function` VALUES (11, '人才培养概括', '/education/training', 'education', 2, NULL, '2019-03-28 17:32:38', NULL);
INSERT INTO `ops_function` VALUES (12, '本科生教学', '/education/postgraduate', 'education', 2, NULL, '2019-03-28 17:32:38', NULL);
INSERT INTO `ops_function` VALUES (13, '课程教学资源', '/education/resource', 'education', 2, NULL, '2019-03-28 17:32:38', NULL);
INSERT INTO `ops_function` VALUES (14, '在职教师', '/teachers/teacher', 'teachers', 3, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (15, '学术交流', '/teachers/academic', 'teachers', 3, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (16, '专业介绍', '/admissionEmployment/major', 'admissionEmployment', 4, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (17, '招生就业概括', '/admissionEmployment/generalize', 'admissionEmployment', 4, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (18, '本科生招生网', '/admissionEmployment/undergraduateAdmission', 'admissionEmployment', 4, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (19, '研究生招生信息', '/admissionEmployment/postgraduateAdmission', 'admissionEmployment', 4, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (20, '毕业生就业指导', '/admissionEmployment/graduateEmployment', 'admissionEmployment', 4, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (21, '创新创业', '/admissionEmployment/innovation', 'admissionEmployment', 4, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (22, '组织结构', '/party/structure', 'party', 5, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (23, '党团快讯', '/party/news', 'party', 5, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (24, '理论学习', '/party/study', 'party', 5, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (25, '工会活动', '/party/activity', 'party', 5, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (26, '学生会概括', '/students/generalize', 'students', 6, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (27, '学工通知', '/students/news', 'students', 6, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (28, '规章制度', '/students/rules', 'students', 6, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (29, '联系电话', '/contactUs/phone', 'contactUs', 7, NULL, '2019-03-28 17:43:25', NULL);
INSERT INTO `ops_function` VALUES (30, '地图导航', '/contactUs/address', 'contactUs', 7, NULL, '2019-06-10 19:50:28', NULL);

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES (1, 0, '系统管理', NULL, NULL, 0, 'fa fa-cog', 0);
INSERT INTO `permissions` VALUES (2, 1, '教师管理', 'manage/student.vm', NULL, 1, 'fa fa-user', 1);
INSERT INTO `permissions` VALUES (3, 1, '角色管理', 'manage/role.vm', NULL, 1, 'fa fa-user-secret', 2);
INSERT INTO `permissions` VALUES (4, 1, '权限管理', 'manage/menu.html', NULL, 1, 'fa fa-th-list', 3);
INSERT INTO `permissions` VALUES (5, 1, '信息管理', 'manage/mange.vm', NULL, 1, 'fa fa-bug', 4);
INSERT INTO `permissions` VALUES (6, 1, '学生管理', 'manage/student.vm', NULL, 1, 'fa fa-tasks', 5);
INSERT INTO `permissions` VALUES (7, 6, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO `permissions` VALUES (8, 6, '新增', NULL, 'sys:user:save', 2, NULL, 0);
INSERT INTO `permissions` VALUES (9, 6, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO `permissions` VALUES (10, 6, '删除', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO `permissions` VALUES (11, 6, '暂停', NULL, 'sys:user:pause', 2, NULL, 0);
INSERT INTO `permissions` VALUES (12, 6, '恢复', NULL, 'sys:user:resume', 2, NULL, 0);
INSERT INTO `permissions` VALUES (14, 6, '日志列表', NULL, 'sys:user:log', 2, NULL, 0);
INSERT INTO `permissions` VALUES (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO `permissions` VALUES (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0);
INSERT INTO `permissions` VALUES (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO `permissions` VALUES (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO `permissions` VALUES (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0);
INSERT INTO `permissions` VALUES (20, 3, '新增', NULL, 'sys:role:save,sys:permissins:perms', 2, NULL, 0);
INSERT INTO `permissions` VALUES (21, 3, '修改', NULL, 'sys:role:update,sys:permissins:perms', 2, NULL, 0);
INSERT INTO `permissions` VALUES (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0);
INSERT INTO `permissions` VALUES (23, 4, '查看', NULL, 'sys:permission:list,sys:permission:info', 2, NULL, 0);
INSERT INTO `permissions` VALUES (24, 4, '新增', NULL, 'sys:permission:save,sys:permission:select', 2, NULL, 0);
INSERT INTO `permissions` VALUES (25, 4, '修改', NULL, 'sys:permission:update,sys:permission:select', 2, NULL, 0);
INSERT INTO `permissions` VALUES (26, 4, '删除', NULL, 'sys:permission:delete', 2, NULL, 0);
INSERT INTO `permissions` VALUES (27, 5, '查看', '', 'sys:message:list,sys:message:info', 1, 'fa fa-sun-o', 6);
INSERT INTO `permissions` VALUES (28, 5, '修改', '', 'sys:message:update', 1, 'fa fa-rocket', 8);
INSERT INTO `permissions` VALUES (29, 5, '删除', '', 'sys:message:dalete', 1, 'fa fa-file-text-o', 7);
INSERT INTO `permissions` VALUES (30, 5, '新增', '', 'sys:message:save,sys:message:select', 1, 'fa fa-file-image-o', 6);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) DEFAULT NULL,
  `modify_date` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `NewIndex1`(`role_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', '0000-00-00 00:00:00', NULL);
INSERT INTO `role` VALUES (2, '院长', '0000-00-00 00:00:00', NULL);
INSERT INTO `role` VALUES (3, '辅导员', '0000-00-00 00:00:00', NULL);
INSERT INTO `role` VALUES (4, '教师', '0000-00-00 00:00:00', NULL);
INSERT INTO `role` VALUES (5, '学生', '0000-00-00 00:00:00', NULL);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  `pid` bigint(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`, `pid`, `rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1, 1);
INSERT INTO `role_permission` VALUES (2, 1, 2);
INSERT INTO `role_permission` VALUES (3, 1, 3);
INSERT INTO `role_permission` VALUES (4, 1, 4);
INSERT INTO `role_permission` VALUES (5, 1, 5);
INSERT INTO `role_permission` VALUES (6, 1, 6);
INSERT INTO `role_permission` VALUES (7, 1, 7);
INSERT INTO `role_permission` VALUES (8, 1, 8);
INSERT INTO `role_permission` VALUES (9, 2, 5);
INSERT INTO `role_permission` VALUES (10, 2, 6);
INSERT INTO `role_permission` VALUES (11, 2, 7);
INSERT INTO `role_permission` VALUES (12, 2, 8);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '表iD',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `code` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号（学号，工号）',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '\r\n密码',
  `telephone_number` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `Email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'null' COMMENT '角色',
  `status` tinyint(4) DEFAULT 1 COMMENT '禁用：0  正常：1',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建用户时间',
  `modify_date` datetime(0) DEFAULT NULL COMMENT '最近一次修改时间',
  PRIMARY KEY (`id`, `code`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '赵敏', '201731771247', 'e10adc3949ba59abbe56e057f20f883e', '18990790791', '1588654@qq.com', NULL, '管理员', 1, '2019-06-17 22:20:19', NULL);
INSERT INTO `user` VALUES (12, '王小', '201731778844', 'e10adc3949ba59abbe56e057f20f883e', '18990790852', '158865422@qq.com', NULL, '学生', 1, '2019-06-18 16:49:16', NULL);
INSERT INTO `user` VALUES (13, '王小小', '201731771111', 'e10adc3949ba59abbe56e057f20f883e', '18990790865', '1522@qq.com', NULL, '管理员', 1, '2019-06-18 16:51:54', NULL);
INSERT INTO `user` VALUES (14, '王老师', '201731771248', '123456', '18990790792', '123@', '男', '管理员', 1, '2019-06-18 21:52:20', NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 5);
INSERT INTO `user_role` VALUES (3, 3, 4);

SET FOREIGN_KEY_CHECKS = 1;
