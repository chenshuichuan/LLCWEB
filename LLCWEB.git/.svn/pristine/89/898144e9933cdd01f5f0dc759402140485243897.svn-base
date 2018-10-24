/*
Navicat MySQL Data Transfer

Source Server         : 120.77.144.151_3306
Source Server Version : 50721
Source Host           : 120.77.144.151:3306
Source Database       : llcweb

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-09-14 20:55:46
*/

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE IF NOT EXISTS llcweb;
USE llcweb;
-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '活动名称',
  `date` date DEFAULT NULL COMMENT '活动日期',
  `introduction` int(11) DEFAULT NULL COMMENT '活动简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', null, null, null);

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` int(11) NOT NULL,
  `people_id` int(11) DEFAULT NULL,
  `people_name` varchar(255) DEFAULT NULL,
  `attendance_date` date DEFAULT NULL COMMENT '签到日期',
  `morning` datetime DEFAULT NULL COMMENT '上午签到时间',
  `afternoon` datetime DEFAULT NULL,
  `evening` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attendance
-- ----------------------------

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award` (
  `title` varchar(255) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of award
-- ----------------------------

-- ----------------------------
-- Table structure for conference
-- ----------------------------
DROP TABLE IF EXISTS `conference`;
CREATE TABLE `conference` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `introduction` int(11) NOT NULL,
  `model` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `people_list` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of conference
-- ----------------------------
INSERT INTO `conference` VALUES ('1', 'haien10', '2018-08-28', '0', '项目组', '项目组第10次会议', '小组会议', null);

-- ----------------------------
-- Table structure for document
-- ----------------------------
DROP TABLE IF EXISTS `document`;
CREATE TABLE `document` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL COMMENT 'people表的作者名称',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `infor` varchar(255) DEFAULT NULL COMMENT '关于该文档的注释',
  `title` varchar(255) DEFAULT NULL COMMENT 'b标题',
  `model` varchar(255) DEFAULT NULL COMMENT '组别',
  `author_id` int(11) NOT NULL COMMENT 'people表的作者id',
  `create_date` date DEFAULT NULL,
  `modify_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of document
-- ----------------------------
INSERT INTO `document` VALUES ('1', 'haien', 'balabala', 'hello infor', '项目组第13次会议', 'helo', '1', '2018-08-30', '2018-08-30');
INSERT INTO `document` VALUES ('2', 'haien2', 'balabala2', 'hello infor', '项目组第2次会议', 'hello', '1', '2018-08-15', '2018-08-30');
INSERT INTO `document` VALUES ('3', 'haien3', 'balabala3', 'hello infor', '项目组第3次会议', 'hello3', '1', '2018-08-23', '2018-08-24');
INSERT INTO `document` VALUES ('4', 'haien4', 'balabala4', 'hello infor', '项目组第4次会议', 'hello3', '30', '2018-08-22', '2018-08-24');
INSERT INTO `document` VALUES ('5', 'haien5', 'balabala5', 'hello3', '项目组第5次会议', 'hello3', '30', '2018-08-29', '2018-08-30');
INSERT INTO `document` VALUES ('6', 'haien6', 'balabala6', 'hello3', '项目组第6次会议', 'model', '0', '2018-08-28', '2018-08-30');
INSERT INTO `document` VALUES ('7', 'haien7', 'balabala7', 'hello infor', '项目组第7次会议', 'hello3', '0', '2018-08-21', '2018-08-30');
INSERT INTO `document` VALUES ('8', 'haien8', 'balabala8', 'hello3', '项目组第8次会议', 'hello3', '0', '2018-08-15', '2018-08-30');
INSERT INTO `document` VALUES ('9', 'haien9', 'balabala9', 'hello infor', '项目组第9次会议', 'hello3', '0', '2018-08-30', '2018-08-30');
INSERT INTO `document` VALUES ('10', 'haien10', 'balabala10', 'hello3', '项目组第10次会议', 'hello3', '0', '2018-08-30', '2018-08-30');
INSERT INTO `document` VALUES ('11', 'hello3', 'haien', 'hello3', 'hello3', 'hello3', '0', '2018-08-30', '2018-08-31');
INSERT INTO `document` VALUES ('12', 'hello3', 'hello3', 'hello3', 'haien', 'hello3', '0', '2018-08-30', '2018-08-31');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `introduction` varchar(50) DEFAULT NULL,
  `model` varchar(10) DEFAULT NULL,
  `owner` varchar(10) DEFAULT NULL,
  `path` varchar(50) DEFAULT NULL,
  `owner_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1', '2018-08-25', '项目组第2次会议', null, 'haien2', null, '0');
INSERT INTO `file` VALUES ('2', '2018-08-25', '项目组第1次会议', null, 'haien', null, '0');

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL COMMENT '照片描述',
  `date` date DEFAULT NULL COMMENT '照片上传日期',
  `owner` varchar(20) DEFAULT NULL COMMENT '照片拥有者、或是上传者',
  `path` varchar(255) DEFAULT NULL COMMENT '片照地址',
  `model` varchar(255) DEFAULT NULL,
  `owner_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES ('10', '项目组第1次会议', '2018-08-20', 'haien1', null, null, '0');
INSERT INTO `image` VALUES ('11', '项目组第2次会议', '2018-08-21', 'haien2', null, null, '0');
INSERT INTO `image` VALUES ('12', '项目组第3次会议', '2018-08-22', 'haien3', null, null, '0');
INSERT INTO `image` VALUES ('13', '项目组第4次会议', '2018-08-23', 'haien4', null, null, '0');
INSERT INTO `image` VALUES ('14', '项目组第5次会议', '2018-08-24', 'haien5', null, null, '0');
INSERT INTO `image` VALUES ('15', '项目组第6次会议', '2018-08-23', 'haien6', null, null, '0');
INSERT INTO `image` VALUES ('16', '项目组第7次会议', '2018-08-25', 'haien7', null, null, '0');
INSERT INTO `image` VALUES ('17', '项目组第8次会议', '2018-08-22', 'haien8', null, null, '0');
INSERT INTO `image` VALUES ('18', '项目组第9次会议', '2018-08-22', 'haien9', null, null, '0');
INSERT INTO `image` VALUES ('19', 'haien', null, null, null, null, '0');
INSERT INTO `image` VALUES ('20', null, null, null, null, 'haien', '0');

-- ----------------------------
-- Table structure for images
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `image_list` varchar(255) DEFAULT NULL COMMENT '影集图片列表、以英文逗号分隔',
  `type` varchar(255) DEFAULT NULL COMMENT '影集类型，对应activity、conference、people、project表等类型',
  `type_id` int(11) DEFAULT NULL COMMENT '应activity、conference、people等表相应的id号',
  `description` varchar(255) DEFAULT NULL COMMENT '影集描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of images
-- ----------------------------

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL COMMENT '题目',
  `date` datetime DEFAULT NULL COMMENT '发表日期',
  `introduction` varchar(11) DEFAULT NULL COMMENT '文章简介',
  `author_list` varchar(255) DEFAULT NULL COMMENT '作者姓名列表，对应people表的姓名，也可以是外面合作人员',
  `original_link` varchar(64) DEFAULT NULL COMMENT '原文链接',
  `source_link` varchar(64) DEFAULT NULL COMMENT '源码链接',
  `belong_project` varchar(11) DEFAULT NULL COMMENT '所属项目id',
  `periodical` varchar(64) DEFAULT NULL COMMENT '发表期刊',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES ('11', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第2区论文');
INSERT INTO `paper` VALUES ('12', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第3区论文');
INSERT INTO `paper` VALUES ('13', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第4区论文');
INSERT INTO `paper` VALUES ('14', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第5区论文');
INSERT INTO `paper` VALUES ('15', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第6区论文');
INSERT INTO `paper` VALUES ('16', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第7区论文');
INSERT INTO `paper` VALUES ('17', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第8区论文');
INSERT INTO `paper` VALUES ('18', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第9区论文');
INSERT INTO `paper` VALUES ('19', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第10区论文');
INSERT INTO `paper` VALUES ('20', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第2区论文');
INSERT INTO `paper` VALUES ('21', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第3区论文');
INSERT INTO `paper` VALUES ('22', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第4区论文');
INSERT INTO `paper` VALUES ('23', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第5区论文');
INSERT INTO `paper` VALUES ('24', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第6区论文');
INSERT INTO `paper` VALUES ('25', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第7区论文');
INSERT INTO `paper` VALUES ('26', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第8区论文');
INSERT INTO `paper` VALUES ('27', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第9区论文');
INSERT INTO `paper` VALUES ('28', 'heiheihei', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第10区论文');
INSERT INTO `paper` VALUES ('29', '语义分割2种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第2区论文');
INSERT INTO `paper` VALUES ('31', '语义分割4种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第4区论文');
INSERT INTO `paper` VALUES ('32', '语义分割5种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第5区论文');
INSERT INTO `paper` VALUES ('33', '语义分割6种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第6区论文');
INSERT INTO `paper` VALUES ('34', '语义分割7种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第7区论文');
INSERT INTO `paper` VALUES ('35', '语义分割8种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第8区论文');
INSERT INTO `paper` VALUES ('36', '语义分割9种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第9区论文');
INSERT INTO `paper` VALUES ('37', '语义分割10种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第10区论文');
INSERT INTO `paper` VALUES ('38', 'GAN2种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第2区论文');
INSERT INTO `paper` VALUES ('39', 'GAN3种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第3区论文');
INSERT INTO `paper` VALUES ('40', 'GAN4种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第4区论文');
INSERT INTO `paper` VALUES ('41', 'GAN5种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第5区论文');
INSERT INTO `paper` VALUES ('42', 'GAN6种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第6区论文');
INSERT INTO `paper` VALUES ('43', 'GAN7种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第7区论文');
INSERT INTO `paper` VALUES ('44', 'GAN8种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第8区论文');
INSERT INTO `paper` VALUES ('46', 'GAN10种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第10区论文');
INSERT INTO `paper` VALUES ('47', 'GAN2种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第2区论文');
INSERT INTO `paper` VALUES ('48', 'GAN3种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第3区论文');
INSERT INTO `paper` VALUES ('49', 'GAN4种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第4区论文');
INSERT INTO `paper` VALUES ('50', 'GAN5种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第5区论文');
INSERT INTO `paper` VALUES ('51', 'GAN6种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第6区论文');
INSERT INTO `paper` VALUES ('52', 'GAN7种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第7区论文');
INSERT INTO `paper` VALUES ('53', 'GAN8种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第8区论文');
INSERT INTO `paper` VALUES ('54', 'GAN9种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第9区论文');
INSERT INTO `paper` VALUES ('55', 'GAN种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第区论文');
INSERT INTO `paper` VALUES ('56', 'GAN2种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第2区论文');
INSERT INTO `paper` VALUES ('57', 'GAN3种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第3区论文');
INSERT INTO `paper` VALUES ('58', 'GAN4种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第4区论文');
INSERT INTO `paper` VALUES ('59', 'GAN5种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第5区论文');
INSERT INTO `paper` VALUES ('60', 'GAN6种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第6区论文');
INSERT INTO `paper` VALUES ('61', 'GAN7种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第7区论文');
INSERT INTO `paper` VALUES ('62', 'GAN8种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第8区论文');
INSERT INTO `paper` VALUES ('63', 'GAN9种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第9区论文');
INSERT INTO `paper` VALUES ('65', 'GAN种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第区论文');
INSERT INTO `paper` VALUES ('66', 'GAN种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第区论文');
INSERT INTO `paper` VALUES ('67', 'GAN种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第区论文');
INSERT INTO `paper` VALUES ('68', 'GAN种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第区论文');
INSERT INTO `paper` VALUES ('69', 'GAN种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第区论文');
INSERT INTO `paper` VALUES ('70', 'GAN种方法综述', '2018-08-28 00:00:00', 'nothing', 'test', 'www.github.com', 'www.google.com', 'hahaha', 'SCI第区论文');

-- ----------------------------
-- Table structure for patent
-- ----------------------------
DROP TABLE IF EXISTS `patent`;
CREATE TABLE `patent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL COMMENT '专利标题',
  `appli_date` datetime DEFAULT NULL COMMENT '申请日',
  `introduction` varchar(255) DEFAULT NULL COMMENT '文章简介',
  `author_list` varchar(128) DEFAULT NULL COMMENT '发明人，作者姓名列表，对应people表的姓名，也可以是外面合作人员',
  `original_link` varchar(64) DEFAULT NULL COMMENT '原文链接',
  `belong_project` varchar(11) DEFAULT NULL COMMENT '所属项目id',
  `appli_num` varchar(11) DEFAULT NULL COMMENT '专利申请号',
  `public_num` varchar(11) DEFAULT NULL COMMENT '专利公开号',
  `public_date` datetime DEFAULT NULL COMMENT '公开日',
  `agency` varchar(128) DEFAULT NULL COMMENT '代理机构',
  `appli_people` varchar(64) DEFAULT NULL COMMENT '申请人（一般为广东工业大学）',
  `application_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patent
-- ----------------------------
INSERT INTO `patent` VALUES ('1', '网络组第1篇专利', null, 'aaaaa1', '3331', '6661', '5551', null, 'CN1', '1970-01-01 08:00:00', 'GDUT', 'hahah', '2018-09-06 20:36:06');
INSERT INTO `patent` VALUES ('2', '网络组第2篇专利', null, 'aaaaa2', '3332', '6662', '5552', null, 'CN2', '1970-01-01 08:00:00', 'GDUT', 'hahah', '2018-09-06 20:36:06');
INSERT INTO `patent` VALUES ('4', '网络组第4篇专利', null, 'aaaaa4', '3334', '6664', '5554', null, 'CN4', '1970-01-01 08:00:00', 'GDUT', 'hahah', '2018-09-06 20:36:07');
INSERT INTO `patent` VALUES ('5', '网络组第5篇专利', null, 'aaaaa5', '3335', '6665', '5555', null, 'CN5', '1970-01-01 08:00:00', 'GDUT', 'hahah', '2018-09-06 20:36:07');
INSERT INTO `patent` VALUES ('6', '网络组第6篇专利', null, 'aaaaa6', '3336', '6666', '5556', null, 'CN6', '1970-01-01 08:00:00', 'GDUT', 'hahah', '2018-09-06 20:36:07');
INSERT INTO `patent` VALUES ('7', '网络组第7篇专利', null, 'aaaaa7', '3337', '6667', '5557', null, 'CN7', '1970-01-01 08:00:00', 'GDUT', 'hahah', '2018-09-06 20:36:08');
INSERT INTO `patent` VALUES ('8', '网络组第8篇专利', null, 'aaaaa8', '3338', '6668', '5558', null, 'CN8', '1970-01-01 08:00:00', 'GDUT', 'hahah', '2018-09-06 20:36:08');

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '人物id',
  `name` varchar(255) DEFAULT NULL COMMENT '人物姓名',
  `passwd` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `portrait` int(11) DEFAULT NULL COMMENT '人物头像图片',
  `position` varchar(255) DEFAULT NULL COMMENT '职位，教授、副教授、讲师、博士后、博士、硕士，本科生',
  `introduction` int(11) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL COMMENT '对于本科生、硕士生博士生是届数，讲师，教授，等是加入年份',
  `ispunch` varchar(50) DEFAULT NULL COMMENT '研究生为Y，其他为N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people
-- ----------------------------

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(64) DEFAULT NULL COMMENT '项目状态',
  `money` varchar(64) DEFAULT NULL COMMENT '项目经费',
  `end_date` date DEFAULT NULL COMMENT '结束时间',
  `start_date` date DEFAULT NULL COMMENT '开始时间',
  `title` varchar(64) DEFAULT NULL COMMENT '负责人职称',
  `responsible_person` varchar(64) DEFAULT NULL COMMENT '项目负责人',
  `require_num` varchar(128) DEFAULT NULL COMMENT '项目编号',
  `project_type` varchar(128) DEFAULT NULL COMMENT '项目类型',
  `project_name` varchar(128) DEFAULT NULL COMMENT '项目名称',
  `members` varchar(128) DEFAULT NULL COMMENT '项目人员',
  `team` varchar(64) DEFAULT NULL COMMENT '所属组别',
  `project_des` text COMMENT '项目描述',
  `project_aim` text COMMENT '项目目标',
  `host_unit` varchar(64) DEFAULT NULL COMMENT '主办单位',
  `co_unit` varchar(64) DEFAULT NULL COMMENT '协办单位',
  `undertake_unit` varchar(64) DEFAULT NULL COMMENT '承办单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=477 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('315', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第1个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('316', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '大数据组第11个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('317', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '大数据组第12个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('318', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '大数据组第13个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('319', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '大数据组第14个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('320', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '大数据组第15个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('321', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '大数据组第16个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('322', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '大数据组第17个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('323', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '大数据组第18个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('324', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '大数据组第19个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('325', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '大数据组第20个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('326', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第1个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('327', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第2个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('328', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第3个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('329', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第4个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('330', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第5个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('331', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第6个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('332', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第7个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('333', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第8个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('334', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第9个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('335', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第10个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('337', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第12个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('338', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第13个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('339', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第14个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('340', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第15个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('341', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第16个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('342', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第17个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('343', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第18个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('344', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第19个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('345', 'ing', '99', '2018-08-28', '2018-08-25', 'title', 're', 'CN', 'xy', '图像组第20个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('346', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第1个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('347', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第2个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('348', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第3个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('349', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第4个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('350', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第5个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('351', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第6个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('352', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第7个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('353', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第8个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('354', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第9个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('355', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第10个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('356', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第11个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('357', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第12个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('358', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第13个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('359', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第14个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('360', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第15个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('361', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第16个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('362', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第17个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('363', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第18个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('364', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第19个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('365', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '太赫兹组第20个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('366', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第1个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('367', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第2个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('368', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第3个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('369', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第4个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('370', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第5个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('371', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第6个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('372', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第7个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('373', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第8个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('374', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第9个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('375', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第10个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('376', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第11个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('377', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第12个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('378', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第13个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('379', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第14个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('380', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第15个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('381', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第16个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('382', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第17个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('383', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第18个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('384', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第19个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('385', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第20个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('386', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第1个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('387', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第2个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('388', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第3个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('389', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第4个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('390', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第5个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('391', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第6个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('392', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第7个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('393', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第8个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('394', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第9个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('395', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第10个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('396', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第11个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('397', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第12个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('398', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第13个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('399', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第14个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('400', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第15个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('401', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第16个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('402', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第17个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('403', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第18个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('404', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第19个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('405', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '机器人组第20个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('406', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第1个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('407', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第2个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('408', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第3个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('409', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第4个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('410', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第5个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('411', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第6个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('412', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第7个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('413', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第8个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('414', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第9个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('415', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第10个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('416', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第11个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('417', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第12个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('418', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第13个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('419', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第14个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('420', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第15个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('421', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第16个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('422', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第17个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('423', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第18个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('424', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第19个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('425', 'ing', '99', '2018-09-02', '2018-09-02', 'title', 're', 'CN', 'xy', '金融组第20个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('461', 'ing', '99', '2018-09-03', '2018-09-03', 'title', 're', 'CN', 'xy', '第二次测试图像组第1个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('462', 'ing', '99', '2018-09-03', '2018-09-03', 'title', 're', 'CN', 'xy', '第二次测试图像组第2个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('463', 'ing', '99', '2018-09-03', '2018-09-03', 'title', 're', 'CN', 'xy', '第二次测试图像组第3个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('464', 'ing', '99', '2018-09-03', '2018-09-03', 'title', 're', 'CN', 'xy', '第二次测试图像组第4个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('465', 'ing', '99', '2018-09-03', '2018-09-03', 'title', 're', 'CN', 'xy', '第二次测试图像组第5个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('466', 'ing', '99', '2018-09-03', '2018-09-03', 'title', 're', 'CN', 'xy', '第二次测试图像组第6个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('467', 'ing', '99', '2018-09-03', '2018-09-03', 'title', 're', 'CN', 'xy', '第二次测试图像组第7个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('468', 'ing', '99', '2018-09-03', '2018-09-03', 'title', 're', 'CN', 'xy', '第二次测试图像组第8个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('469', 'ing', '99', '2018-09-06', '2018-09-06', 'title', 're', 'CN', 'xy', '第二次测试图像组第1个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('470', 'ing', '99', '2018-09-06', '2018-09-06', 'title', 're', 'CN', 'xy', '第二次测试图像组第2个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('471', 'ing', '99', '2018-09-06', '2018-09-06', 'title', 're', 'CN', 'xy', '第二次测试图像组第3个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('472', 'ing', '99', '2018-09-06', '2018-09-06', 'title', 're', 'CN', 'xy', '第二次测试图像组第4个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('473', 'ing', '99', '2018-09-06', '2018-09-06', 'title', 're', 'CN', 'xy', '第二次测试图像组第5个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('474', 'ing', '99', '2018-09-06', '2018-09-06', 'title', 're', 'CN', 'xy', '第二次测试图像组第6个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('475', 'ing', '99', '2018-09-06', '2018-09-06', 'title', 're', 'CN', 'xy', '第二次测试图像组第7个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');
INSERT INTO `project` VALUES ('476', 'ing', '99', '2018-09-06', '2018-09-06', 'title', 're', 'CN', 'xy', '第二次测试图像组第8个项目', 'who', '999', 'test', 'testPassed', 'GDUT', 'GDUT', 'GDUT');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `r_id` int(11) NOT NULL,
  `r_name` varchar(32) DEFAULT NULL,
  `r_flag` varchar(32) DEFAULT NULL,
  `rId` int(11) NOT NULL,
  `rFlag` varchar(255) DEFAULT NULL,
  `rName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', '系统管理员', 'ADMIN', '0', null, null);
INSERT INTO `roles` VALUES ('2', '组长', 'GROUP', '0', null, null);
INSERT INTO `roles` VALUES ('3', '成员', 'USER', '0', null, null);

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL COMMENT '编写者',
  `title` varchar(255) DEFAULT NULL COMMENT '任务标题',
  `detail` varchar(255) DEFAULT NULL COMMENT '任务详情',
  `type` varchar(255) DEFAULT NULL COMMENT '任务类型，个人，项目组，实验室',
  `status` varchar(255) DEFAULT NULL COMMENT '任务状态，未完成、已完成',
  `end_date` datetime DEFAULT NULL COMMENT '任务截止时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `username` varchar(255) NOT NULL COMMENT 'user name',
  `password` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `people_id` int(11) NOT NULL COMMENT '对应的people的id',
  `team` varchar(255) DEFAULT NULL COMMENT '所属组别，组长查询组内的相关工作',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', 'admin', '2018-08-15 22:40:55', '0', 'car');
INSERT INTO `users` VALUES ('29', 'user0', 'password0', '2018-08-29 22:40:46', '0', 'car');
INSERT INTO `users` VALUES ('30', 'user1', 'password1', '2018-08-29 22:40:47', '1', 'car');
INSERT INTO `users` VALUES ('31', 'user2', 'password2', '2018-08-29 22:40:47', '2', 'car');
INSERT INTO `users` VALUES ('32', 'user3', 'password3', '2018-08-29 22:40:47', '3', 'car');
INSERT INTO `users` VALUES ('33', 'user4', 'password4', '2018-08-29 22:40:47', '4', 'car');
INSERT INTO `users` VALUES ('34', 'user5', 'password5', '2018-08-29 22:40:47', '5', null);
INSERT INTO `users` VALUES ('35', 'user6', 'password6', '2018-08-29 22:40:47', '6', null);
INSERT INTO `users` VALUES ('36', 'user7', 'password7', '2018-08-29 22:40:47', '7', null);
INSERT INTO `users` VALUES ('37', 'user8', 'password8', '2018-08-29 22:40:47', '8', null);
INSERT INTO `users` VALUES ('38', 'user9', 'password9', '2018-08-29 22:40:47', '9', null);
INSERT INTO `users` VALUES ('39', 'user10', 'password10', '2018-08-29 22:40:47', '10', null);
INSERT INTO `users` VALUES ('40', 'user11', 'password11', '2018-08-29 22:40:47', '11', null);
INSERT INTO `users` VALUES ('41', 'user12', 'password12', '2018-08-29 22:40:47', '12', null);
INSERT INTO `users` VALUES ('42', 'user13', 'password13', '2018-08-29 22:40:47', '13', null);
INSERT INTO `users` VALUES ('43', 'who', 'password14', '2018-08-29 22:40:47', '14', null);

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `ur_id` int(11) NOT NULL,
  `ur_user_id` int(11) NOT NULL,
  `ur_role_id` int(11) NOT NULL,
  `limit_pages` varchar(255) DEFAULT NULL COMMENT '对该角色限制的页面',
  PRIMARY KEY (`ur_id`),
  KEY `FK7s0n3og3wig311tb5281wvw73` (`ur_role_id`),
  KEY `FKbjkk0haeed2fyyfvnaqrjdqur` (`ur_user_id`),
  CONSTRAINT `FK7s0n3og3wig311tb5281wvw73` FOREIGN KEY (`ur_role_id`) REFERENCES `roles` (`r_id`),
  CONSTRAINT `FKbjkk0haeed2fyyfvnaqrjdqur` FOREIGN KEY (`ur_user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `users_roles` VALUES ('1', '1', '1', null);
INSERT INTO `users_roles` VALUES ('2', '1', '2', null);
INSERT INTO `users_roles` VALUES ('3', '29', '2', null);
INSERT INTO `users_roles` VALUES ('4', '30', '3', null);
