/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : commnuity

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-08 17:30:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `email` varchar(255) NOT NULL COMMENT '用户邮箱',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `username` varchar(255) NOT NULL COMMENT '用户昵称',
  `status` int(1) NOT NULL COMMENT '用户状态',
  `reg_time` datetime NOT NULL COMMENT '注册时间',
  `last_update_time` datetime NOT NULL COMMENT '最后修改时间',
  `reg_ip` varchar(255) NOT NULL COMMENT '注册IP',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2931164745@qq.com', '123456', '小千', '0', '2018-03-08 17:25:35', '2018-03-08 17:25:39', '127.0.0.1');
INSERT INTO `user` VALUES ('2', 'lishiqian@qq.com', '123456', '王小二', '0', '2018-03-08 17:27:13', '2018-03-08 17:26:58', '127.0.0.1');
