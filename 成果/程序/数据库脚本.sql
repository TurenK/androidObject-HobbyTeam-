/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : hobbyteam

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-07-21 20:54:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `phonenum` varchar(15) NOT NULL,
  `username` varchar(15) NOT NULL DEFAULT 'gay',
  `password` varchar(15) NOT NULL,
  `headimg` varchar(50) NOT NULL DEFAULT '/upload/user/1.png',
  `sex` char(2) NOT NULL DEFAULT 'm',
  `addr` varchar(100) NOT NULL DEFAULT '北京交通大学逸夫楼4楼男厕所',
  `intro` text,
  `intimeaddr` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`phonenum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('111', 'gay', 'aaa', '/upload/user/1.png', 'm', '北京交通大学逸夫楼4楼男厕所', 'aaa', 'aaaa');

-- ----------------------------
-- Table structure for `activity`
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL,
  `ownerid` int(11) NOT NULL,
  `content` text NOT NULL,
  `commentid` int(11) NOT NULL,
  `supportid` int(11) NOT NULL,
  `img` varchar(3000) NOT NULL,
  `ssortz` int(11) NOT NULL,
  `addr` varchar(3000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------

-- ----------------------------
-- Table structure for `activityaccusation`
-- ----------------------------
DROP TABLE IF EXISTS `activityaccusation`;
CREATE TABLE `activityaccusation` (
  `userid` int(11) NOT NULL,
  `activityid` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `content` text,
  PRIMARY KEY (`userid`,`activityid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activityaccusation
-- ----------------------------

-- ----------------------------
-- Table structure for `activitytag`
-- ----------------------------
DROP TABLE IF EXISTS `activitytag`;
CREATE TABLE `activitytag` (
  `activitytag` int(11) NOT NULL,
  `activityid` int(11) NOT NULL,
  PRIMARY KEY (`activitytag`,`activityid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activitytag
-- ----------------------------

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `activityid` int(11) NOT NULL,
  `content` text NOT NULL,
  `img` varchar(3000) DEFAULT NULL,
  `userid` int(11) NOT NULL,
  `supportid` int(11) NOT NULL,
  PRIMARY KEY (`id`,`activityid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `commentaccusation`
-- ----------------------------
DROP TABLE IF EXISTS `commentaccusation`;
CREATE TABLE `commentaccusation` (
  `userid` int(11) NOT NULL,
  `commentid` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `content` text,
  PRIMARY KEY (`userid`,`commentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commentaccusation
-- ----------------------------

-- ----------------------------
-- Table structure for `contact`
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `user1id` int(11) NOT NULL,
  `user2id` int(11) NOT NULL,
  `1to2alias` varchar(15) NOT NULL,
  `2to1alias` varchar(15) NOT NULL,
  PRIMARY KEY (`user1id`,`user2id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact
-- ----------------------------

-- ----------------------------
-- Table structure for `preferenceteam`
-- ----------------------------
DROP TABLE IF EXISTS `preferenceteam`;
CREATE TABLE `preferenceteam` (
  `userid` int(11) NOT NULL,
  `teamid` int(11) NOT NULL,
  PRIMARY KEY (`userid`,`teamid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of preferenceteam
-- ----------------------------

-- ----------------------------
-- Table structure for `support`
-- ----------------------------
DROP TABLE IF EXISTS `support`;
CREATE TABLE `support` (
  `activityid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`activityid`,`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of support
-- ----------------------------

-- ----------------------------
-- Table structure for `team`
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` int(11) NOT NULL,
  `name` varchar(15) NOT NULL,
  `intro` text NOT NULL,
  `headimg` varchar(50) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team
-- ----------------------------

-- ----------------------------
-- Table structure for `team_user`
-- ----------------------------
DROP TABLE IF EXISTS `team_user`;
CREATE TABLE `team_user` (
  `userid` int(11) NOT NULL,
  `teamid` int(11) NOT NULL,
  PRIMARY KEY (`userid`,`teamid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team_user
-- ----------------------------

-- ----------------------------
-- Table structure for `usertag`
-- ----------------------------
DROP TABLE IF EXISTS `usertag`;
CREATE TABLE `usertag` (
  `userid` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`userid`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usertag
-- ----------------------------
