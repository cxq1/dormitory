/*
Navicat MySQL Data Transfer

Source Server         : localhost_3307
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : dormitory

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2020-02-09 14:50:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power` (
  `id` varchar(255) NOT NULL,
  `role_name` char(30) NOT NULL,
  `power_rule` char(50) DEFAULT NULL COMMENT '权限规则',
  `power_name` char(30) DEFAULT NULL,
  `info` char(255) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`),
  KEY `role_name` (`role_name`),
  CONSTRAINT `power_ibfk_1` FOREIGN KEY (`role_name`) REFERENCES `role` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES ('1111', 'admin', '/dormitory/power/list', '权限列表', '权限列表', '0');
INSERT INTO `power` VALUES ('2222', 'admin', '/dormitory/power/add', '添加权限', '添加权限', '0');

-- ----------------------------
-- Table structure for reserve
-- ----------------------------
DROP TABLE IF EXISTS `reserve`;
CREATE TABLE `reserve` (
  `id` varchar(255) NOT NULL,
  `username` char(255) NOT NULL,
  `rno` char(255) NOT NULL COMMENT '房间号',
  `order_date` datetime NOT NULL COMMENT '预订日期',
  `r_in_data` char(20) NOT NULL COMMENT '入住时间',
  `day_num` int(11) NOT NULL DEFAULT '1' COMMENT '预定天数',
  `reason` char(255) NOT NULL,
  `note` char(255) DEFAULT NULL COMMENT '预定备注',
  `checked` char(20) NOT NULL COMMENT '是否审核',
  `gmt_modify_time` datetime DEFAULT NULL,
  `gmt_created_time` datetime NOT NULL COMMENT '预订时间',
  PRIMARY KEY (`id`),
  KEY `rno` (`rno`),
  CONSTRAINT `reserve_ibfk_1` FOREIGN KEY (`rno`) REFERENCES `roominfo` (`rno`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reserve
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(255) NOT NULL,
  `role_rule` char(255) DEFAULT NULL COMMENT '角色规则',
  `role_name` char(30) NOT NULL,
  `info` char(255) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `rolename` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1111', '所有权限', 'admin', '管理员权限', '0');

-- ----------------------------
-- Table structure for roomcost
-- ----------------------------
DROP TABLE IF EXISTS `roomcost`;
CREATE TABLE `roomcost` (
  `id` varchar(255) NOT NULL,
  `rno` char(20) NOT NULL COMMENT '房间号',
  `number` char(18) NOT NULL COMMENT '证件号',
  `indate` datetime DEFAULT NULL COMMENT '入住日期',
  `mvdate` datetime DEFAULT NULL COMMENT '离开日期',
  `price` char(10) DEFAULT NULL COMMENT '房价',
  `prepay` char(10) DEFAULT NULL COMMENT '预付',
  `gmt_created_time` datetime DEFAULT NULL,
  `gmt_modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rno` (`rno`),
  CONSTRAINT `roomcost_ibfk_1` FOREIGN KEY (`rno`) REFERENCES `roominfo` (`rno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roomcost
-- ----------------------------

-- ----------------------------
-- Table structure for roominfo
-- ----------------------------
DROP TABLE IF EXISTS `roominfo`;
CREATE TABLE `roominfo` (
  `id` varchar(255) NOT NULL,
  `rno` char(20) NOT NULL COMMENT '房间号',
  `rtype` char(20) NOT NULL,
  `rposition` char(50) DEFAULT NULL COMMENT '房间位置',
  `rstatus` char(20) DEFAULT NULL COMMENT '房间状态',
  `rnote` char(255) DEFAULT NULL COMMENT '房间备注',
  `rprice` char(20) DEFAULT NULL,
  `gmt_create_time` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_modify_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `rno` (`rno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roominfo
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` varchar(255) NOT NULL,
  `name` char(30) NOT NULL COMMENT '真实姓名',
  `username` char(16) NOT NULL COMMENT '昵称',
  `password` char(255) NOT NULL,
  `sex` enum('男','女') NOT NULL,
  `phone` char(11) NOT NULL,
  `mail` char(32) NOT NULL,
  `number` char(18) NOT NULL,
  `role_name` char(15) NOT NULL DEFAULT '',
  `status` tinyint(4) DEFAULT NULL COMMENT '是否可用',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐值',
  `gmt_created_time` datetime DEFAULT NULL,
  `gmt_modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
