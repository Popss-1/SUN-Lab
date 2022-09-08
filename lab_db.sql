/*
 Navicat Premium Data Transfer

 Source Server         : localhonst
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : lab_db

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 09/03/2022 14:59:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accesslog
-- ----------------------------
DROP TABLE IF EXISTS `accesslog`;
CREATE TABLE `accesslog` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `uname` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` int(10) DEFAULT NULL,
  `status` int(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10009 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (10000, 'admin', 0, 1, 'admin');
INSERT INTO `user` VALUES (10001, 'Bob', 2, 1, '111');
INSERT INTO `user` VALUES (10002, 'John', 1, 1, '111');
INSERT INTO `user` VALUES (10003, 'jack', 2, 1, '111');
INSERT INTO `user` VALUES (10004, 'Abel', 2, 1, '111');
INSERT INTO `user` VALUES (10005, 'Bert', 2, 1, '111');
INSERT INTO `user` VALUES (10006, 'Anna', 2, 1, '111');
INSERT INTO `user` VALUES (10007, 'Jane', 2, 1, '111');
INSERT INTO `user` VALUES (10008, 'Lois', 2, 1, '111');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
