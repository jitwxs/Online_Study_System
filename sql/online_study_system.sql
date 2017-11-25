/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : online_study_system

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-11-26 00:00:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(26) NOT NULL COMMENT '课程名称',
  `teacher_id` int(11) NOT NULL COMMENT '教授老师工号',
  `period` int(11) DEFAULT NULL COMMENT '学时',
  `price` int(11) NOT NULL COMMENT '售价',
  PRIMARY KEY (`id`),
  KEY `teacherID` (`teacher_id`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'C语言从入门到放弃', '25', '20', '10');
INSERT INTO `course` VALUES ('4', 'JAVA', '26', '18', '20');
INSERT INTO `course` VALUES ('7', '养猪入门', '38', '20', '10');
INSERT INTO `course` VALUES ('8', '从零开始入门炒股', '26', '50', '74');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL COMMENT '类型名',
  `permissions` varchar(255) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', null);
INSERT INTO `role` VALUES ('2', 'teacher', null);
INSERT INTO `role` VALUES ('3', 'student', null);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `name` varchar(16) NOT NULL COMMENT '姓名',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `register_time` date DEFAULT NULL COMMENT '注册日期',
  `balance` int(64) DEFAULT '0' COMMENT '账户余额',
  PRIMARY KEY (`id`),
  CONSTRAINT `student_userlogin_FK` FOREIGN KEY (`id`) REFERENCES `userlogin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('24', '小明', '男', '2017-10-03', '2017-11-03', '80');
INSERT INTO `student` VALUES ('39', '小红', '男', '1995-01-10', '2017-11-05', '0');
INSERT INTO `student` VALUES ('40', '小林', '女', '1990-06-01', '2017-11-05', '0');

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `progress` int(11) NOT NULL DEFAULT '1' COMMENT '当前学时',
  `mark` int(11) DEFAULT NULL COMMENT '成绩',
  `has_exam` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否完成考试（0：未完成；1：完成）',
  KEY `courseID` (`course_id`),
  KEY `studentID` (`student_id`),
  CONSTRAINT `selectedcourse_student_FK` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_course_course_FK` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES ('1', '24', '0', null, '0');
INSERT INTO `student_course` VALUES ('4', '24', '18', '10', '1');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL COMMENT '姓名',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `degree` varchar(20) DEFAULT NULL COMMENT '学历',
  `register_time` date DEFAULT NULL COMMENT '注册日期',
  PRIMARY KEY (`id`),
  CONSTRAINT `teacher_userlogin_FK` FOREIGN KEY (`id`) REFERENCES `userlogin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('25', '李老师', '男', '2000-11-03', '本科', '2017-11-03');
INSERT INTO `teacher` VALUES ('26', '张老师', '女', '2017-11-03', '研究生', '2017-11-03');
INSERT INTO `teacher` VALUES ('37', '刘老师', '男', '1997-11-13', '硕士', '2017-11-05');
INSERT INTO `teacher` VALUES ('38', '曹老师', '男', '1958-11-12', '博士', '2017-11-05');

-- ----------------------------
-- Table structure for userlogin
-- ----------------------------
DROP TABLE IF EXISTS `userlogin`;
CREATE TABLE `userlogin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL COMMENT '姓名',
  `password` char(56) NOT NULL COMMENT '密码',
  `role` int(2) NOT NULL DEFAULT '2' COMMENT '角色权限',
  PRIMARY KEY (`id`),
  KEY `role` (`role`),
  CONSTRAINT `userLogin_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userlogin
-- ----------------------------
INSERT INTO `userlogin` VALUES ('23', 'admin', 'b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d', '1');
INSERT INTO `userlogin` VALUES ('24', '小明', 'b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d', '3');
INSERT INTO `userlogin` VALUES ('25', '李老师', 'b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d', '2');
INSERT INTO `userlogin` VALUES ('26', '张老师', 'b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d', '2');
INSERT INTO `userlogin` VALUES ('37', '刘老师', 'b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d', '2');
INSERT INTO `userlogin` VALUES ('38', '曹老师', 'b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d', '2');
INSERT INTO `userlogin` VALUES ('39', '小红', 'b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d', '3');
INSERT INTO `userlogin` VALUES ('40', '小林', 'b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d', '3');
