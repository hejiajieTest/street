/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : semi

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-11-28 14:28:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_manual_info
-- ----------------------------
DROP TABLE IF EXISTS `t_manual_info`;
CREATE TABLE `t_manual_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `manual_name` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `manual_desc` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `dowload_url` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `auto_enterprise_code` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `manual_type` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '1-车机APP手册 \r\n2-手机APP 手册\r\n3-公众门户手册 \r\n4-车厂监控中心手册 \r\n5-运维管理平台手册\r\n',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='记录用户手册信息';
