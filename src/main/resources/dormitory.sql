/*
Navicat MySQL Data Transfer

Source Server         : asd
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : dormitory

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-03-12 16:13:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `power`
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
  KEY `role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES ('1', 'admin', '/dormitory/role/*', '角色功能', '角色添加', '0');
INSERT INTO `power` VALUES ('1111', 'admin1', '/dormitory/power/list', '权限列表', '权限列表', '0');
INSERT INTO `power` VALUES ('12', 'admin', '/dormitory/power/*', '权限规则', '权限规则', '0');
INSERT INTO `power` VALUES ('1227151820699262978', 'string11', '/dormitory/power/add', 'ttt', 'ddd', '0');
INSERT INTO `power` VALUES ('1227152414092615681', 'ptuser', 'test', 'test', 'fdtet', '0');
INSERT INTO `power` VALUES ('1227153024292544514', 'string11', '/dormitory/power/add', '添加tet', 'tetttttttt', '0');
INSERT INTO `power` VALUES ('123', 'admin', '/dormitory/user/*', '用户功能', '用户功能', '0');
INSERT INTO `power` VALUES ('1231111747342331906', 'ptuser', 'test', 'ttest', '', '0');
INSERT INTO `power` VALUES ('1232177888346808321', 'ptuser', '/dormitory/roominfo/list', '住宿记录查看', '住宿记录查看', '0');
INSERT INTO `power` VALUES ('1232189081153302530', 'admin', 'dormitory/roominfo/admin/list', '管理员住宿记录查看', '管理员住宿记录查看', '0');
INSERT INTO `power` VALUES ('1234', 'admin', '/dormitory/reserve/*', '審核', '審核', '0');
INSERT INTO `power` VALUES ('2222', 'admin', '/dormitory/power/add', '添加权限', 'string添加权限eeetttddsa', '0');
INSERT INTO `power` VALUES ('33', 'admin', '/dormitory/power/add', '/dormitory/power/add', '/dormitory/power/add', '0');
INSERT INTO `power` VALUES ('4325', 'admin', '/dormitory/role/list', '角色列表', '角色列表', '0');
INSERT INTO `power` VALUES ('454', 'admin', 'page/admin-list', 'yem', 'ye', '0');

-- ----------------------------
-- Table structure for `reserve`
-- ----------------------------
DROP TABLE IF EXISTS `reserve`;
CREATE TABLE `reserve` (
  `id` varchar(255) NOT NULL,
  `name` char(255) NOT NULL,
  `uid` char(255) NOT NULL,
  `rno` char(255) NOT NULL COMMENT '房间号',
  `order_date` datetime DEFAULT NULL COMMENT '预订日期',
  `r_in_date` datetime NOT NULL COMMENT '入住时间',
  `day_num` int(11) NOT NULL DEFAULT '1' COMMENT '预定天数',
  `reason` char(255) NOT NULL,
  `note` char(255) DEFAULT NULL COMMENT '预定备注',
  `checked` char(20) NOT NULL DEFAULT '1' COMMENT '是否审核',
  `gmt_modify_time` datetime DEFAULT NULL,
  `gmt_created_time` datetime DEFAULT NULL COMMENT '预订时间',
  PRIMARY KEY (`id`),
  KEY `rno` (`rno`),
  CONSTRAINT `reserve_ibfk_1` FOREIGN KEY (`rno`) REFERENCES `roominfo` (`rno`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reserve
-- ----------------------------
INSERT INTO `reserve` VALUES ('1236138957553545217', 'a', '1232908258860138497', '508', '2020-03-07 11:57:48', '2020-03-07 11:57:11', '4', '111', '666', '0', '2020-03-07 11:57:48', '2020-03-07 11:57:48');
INSERT INTO `reserve` VALUES ('1236138998527700993', 'a', '1232908258860138497', '301', '2020-03-07 11:57:58', '2020-03-07 11:57:54', '1', '222', '222', '0', '2020-03-07 11:57:58', '2020-03-07 11:57:58');
INSERT INTO `reserve` VALUES ('1236245900272115714', 'a', '1232908258860138497', '508', '2020-03-07 19:02:46', '2020-03-14 19:02:41', '2', '000', '000', '1', '2020-03-10 16:40:00', '2020-03-07 19:02:46');
INSERT INTO `reserve` VALUES ('1236246122058522625', 'a', '1232908258860138497', '222', '2020-03-07 19:03:38', '2020-03-07 19:03:35', '3', '222', '222', '1', '2020-03-07 19:03:38', '2020-03-07 19:03:38');
INSERT INTO `reserve` VALUES ('1236246277247770626', 'a', '1232908258860138497', '508', '2020-03-07 19:04:15', '2020-03-07 19:03:57', '5', '777777', '777777', '1', '2020-03-10 17:02:39', '2020-03-07 19:04:15');
INSERT INTO `reserve` VALUES ('1237688041238237186', 'a', '1232908258860138497', '301', '2020-03-11 18:33:19', '2020-03-24 00:00:00', '5', 'hahaahahahah', 'hahaahahahah', '1', '2020-03-11 18:33:19', '2020-03-11 18:33:19');

-- ----------------------------
-- Table structure for `role`
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
INSERT INTO `role` VALUES ('1113', '用户权限', 'ptuser', '普通用户', '0');
INSERT INTO `role` VALUES ('2222', 'string1112222', 'string11', 'string', '0');
INSERT INTO `role` VALUES ('545', 'test', 'admin2', '54dfd', '0');

-- ----------------------------
-- Table structure for `roomcost`
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
-- Table structure for `roominfo`
-- ----------------------------
DROP TABLE IF EXISTS `roominfo`;
CREATE TABLE `roominfo` (
  `id` varchar(255) NOT NULL,
  `rno` char(20) NOT NULL COMMENT '房间号',
  `rtype` char(20) NOT NULL,
  `rposition` char(50) DEFAULT NULL COMMENT '房间位置',
  `rstatus` char(20) DEFAULT NULL COMMENT '房间状态是否有空0代表空1代表满',
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
INSERT INTO `roominfo` VALUES ('12', '508', '双人', '3', '1', '双人', '321', '2020-02-25 14:35:39', '2020-03-07 19:04:16');
INSERT INTO `roominfo` VALUES ('3333', '222', '双人', '3', '1', '双人', '333', '2020-03-05 13:15:47', '2020-03-07 19:03:39');
INSERT INTO `roominfo` VALUES ('777', '201', '单人', '7', '0', '单人', '2222', '2020-02-13 15:53:31', '2020-03-11 18:34:13');
INSERT INTO `roominfo` VALUES ('963', '301', '单人', '6', '1', '单人', '123', '2020-02-25 14:35:20', '2020-03-11 18:33:19');

-- ----------------------------
-- Table structure for `sys_token`
-- ----------------------------
DROP TABLE IF EXISTS `sys_token`;
CREATE TABLE `sys_token` (
  `user_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `token` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_token
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` varchar(255) NOT NULL,
  `name` char(30) NOT NULL COMMENT '真实姓名',
  `username` char(16) NOT NULL COMMENT '昵称',
  `password` char(255) NOT NULL,
  `sex` enum('男','女') NOT NULL,
  `phone` char(11) DEFAULT NULL,
  `mail` char(32) DEFAULT NULL,
  `number` char(18) DEFAULT NULL,
  `role_name` char(15) NOT NULL DEFAULT '',
  `status` tinyint(4) DEFAULT '0' COMMENT '是否可用',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐值',
  `gmt_created_time` datetime DEFAULT NULL,
  `gmt_modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('111', 'cxq', 'cxq', 'e53d6fed228a881c35eaf734f104054e', '男', '13676562115', '198@qq.com', '201731960115', 'admin', '0', '', '2020-02-12 14:34:11', '2020-02-13 14:58:29');
INSERT INTO `user` VALUES ('1227832890566336514', 'string', 'string', 'string', '男', 'string', 'string', 'string', 'string11', '0', 'string', '2020-02-13 13:52:27', '2020-02-14 12:27:15');
INSERT INTO `user` VALUES ('1227836565690998785', 'cxq1', 'cxqzimo', 'cxq1111', '男', '11111111', '1986541496@qq.com', '330327199906221576', '1', '0', null, '2020-02-13 14:07:04', '2020-02-16 15:02:12');
INSERT INTO `user` VALUES ('1230109399841222658', 'zimo', 'zimo', '4a701c6510d3026109b14a16e4c5f329', '男', null, null, null, 'admin', '0', null, '2020-02-19 20:38:29', '2020-02-22 12:38:36');
INSERT INTO `user` VALUES ('1230373428459352065', 'zimo1', 'zimo1', '659f8a9367381209ef05880a4229cfdb', '男', null, null, null, 'admin', '0', null, '2020-02-20 14:07:39', '2020-02-20 14:07:39');
INSERT INTO `user` VALUES ('1231135308530466817', 'ttt', 'tttt', '6f71a80f5f1b03db84d805d5e464cdfa', '男', '11111111111', '1986541496@qq.com', '330327199906221576', '1', '1', null, '2020-02-22 16:35:05', '2020-03-10 16:10:29');
INSERT INTO `user` VALUES ('1231136116886114305', 't', 't', 'a0d17f24b5d54966eab65db5346860df', '男', null, null, null, 'ptuser', '0', null, '2020-02-22 16:38:18', '2020-02-22 17:08:04');
INSERT INTO `user` VALUES ('1232172559068340226', 's', 's', 'c3955fb14c57ced2c3c0d27d2df26400', '男', '3321', 's', 's', 'admin', '0', null, '2020-02-25 13:16:45', '2020-02-25 13:16:45');
INSERT INTO `user` VALUES ('1232908258860138497', 'a', '123', 'd9deacb0d401c86d2adfb14eb548d244', '男', '3213213', '20', 'a', 'ptuser', '0', null, '2020-02-27 14:00:10', '2020-03-10 15:46:42');
INSERT INTO `user` VALUES ('1232930379489460225', 'a', 'aaa', '065394ec7c46987f422bd0ec04d6b3ca', '男', 'aaa', 'aaa', 'a', 'ptuser', '0', null, '2020-02-27 15:28:04', '2020-02-27 15:44:50');
INSERT INTO `user` VALUES ('1235529995716132865', '666', '666', '952d5f79023ff59885b75042b990850c', '男', '666', '666', '666', 'ptuser', '0', null, '2020-03-05 19:38:01', '2020-03-05 19:38:01');
INSERT INTO `user` VALUES ('222', 'test', 'teste', '111', '女', '2222', '434@qq.com', '1111', 'admin', '0', '', '2020-02-12 14:55:22', '2020-02-13 13:33:45');
INSERT INTO `user` VALUES ('4343', 'admin', 'admin', '123456', '男', '2222', '1342@qq.com', '111111', 'admin', '0', null, '2020-02-14 19:48:17', '2020-02-14 19:48:30');
INSERT INTO `user` VALUES ('435', 'test', 'teste', 'string', '男', '2222', '434@qq.com', '1111', '1', '0', '', '2020-02-12 15:22:35', '2020-02-13 14:46:05');
INSERT INTO `user` VALUES ('4444', 'string', 'tes', 'string', '男', 'string', 'string', 'string', 'admin', '0', 'string', '2020-02-12 15:22:35', '2020-02-14 12:27:49');
INSERT INTO `user` VALUES ('4643', 'test', 'teste', '111', '男', '2222', '434@qq.com', '1111', '', '0', '', '2020-02-12 15:22:35', '2020-02-12 15:22:38');
INSERT INTO `user` VALUES ('53463', 'test', 'teste', '111', '男', '2222', '434@qq.com', '1111', '', '0', '', '2020-02-12 15:22:35', '2020-02-12 15:22:38');
INSERT INTO `user` VALUES ('5463', 'test', 'teste', '111', '男', '2222', '434@qq.com', '1111', '', '0', '', '2020-02-12 15:22:35', '2020-02-12 15:22:38');
INSERT INTO `user` VALUES ('54645', 'test', 'teste', '111', '男', '2222', '434@qq.com', '1111', '', '0', '', '2020-02-12 15:22:35', '2020-02-12 15:22:38');
INSERT INTO `user` VALUES ('546456', 'test', 'teste', '111', '男', '2222', '434@qq.com', '1111', '', '0', '', '2020-02-12 15:22:35', '2020-02-12 15:22:38');
INSERT INTO `user` VALUES ('54654', 'test', 'teste', '111', '男', '2222', '434@qq.com', '1111', '', '0', '', '2020-02-12 15:22:35', '2020-02-12 15:22:38');
INSERT INTO `user` VALUES ('6475', 'test', 'teste', '111', '男', '2222', '434@qq.com', '1111', '', '0', '', '2020-02-12 15:22:35', '2020-02-12 15:22:38');
INSERT INTO `user` VALUES ('65437', 'test', 'teste', '111', '男', '2222', '434@qq.com', '1111', '', '0', '', '2020-02-12 15:22:35', '2020-02-12 15:22:38');
INSERT INTO `user` VALUES ('654675', 'test', 'teste', '111', '男', '2222', '434@qq.com', '1111', '', '0', '', '2020-02-12 15:22:35', '2020-02-12 15:22:38');
INSERT INTO `user` VALUES ('6547', 'test', 'teste', '111', '男', '2222', '434@qq.com', '1111', '', '0', '', '2020-02-12 15:22:35', '2020-02-12 15:22:38');
INSERT INTO `user` VALUES ('65634', 'test', 'teste', '111', '男', '2222', '434@qq.com', '1111', '', '0', '', '2020-02-12 15:22:35', '2020-02-12 15:22:38');
INSERT INTO `user` VALUES ('6565', 'test', 'teste', '111', '男', '2222', '434@qq.com', '1111', '', '0', '', '2020-02-12 15:22:35', '2020-02-12 15:22:38');
INSERT INTO `user` VALUES ('745', 'test', 'teste', '111', '男', '2222', '434@qq.com', '1111', '', '0', '', '2020-02-12 15:22:35', '2020-02-12 15:22:38');
INSERT INTO `user` VALUES ('7567', 'test', 'teste', '111', '男', '2222', '434@qq.com', '1111', '', '0', '', '2020-02-12 15:22:35', '2020-02-12 15:22:38');
INSERT INTO `user` VALUES ('a', 'a', 'aaaaaa', '6cddf1aadaf7cdc331bab130211130ac', '男', 'aaa', 'abc', 'a', 'ptuser', '0', null, '2020-02-26 15:34:38', '2020-02-26 15:35:23');
