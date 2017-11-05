-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: online_study_system
-- ------------------------------------------------------
-- Server version	5.7.18-1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'C语言从入门到放弃',25,20,10),(4,'JAVA',26,18,20),(7,'养猪入门',38,20,10),(8,'从零开始入门炒股',26,50,74);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL COMMENT '类型名',
  `permissions` varchar(255) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin',NULL),(2,'teacher',NULL),(3,'student',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `name` varchar(16) NOT NULL COMMENT '姓名',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `register_time` date DEFAULT NULL COMMENT '注册日期',
  `balance` int(64) DEFAULT '0' COMMENT '账户余额',
  PRIMARY KEY (`id`),
  CONSTRAINT `student_userlogin_FK` FOREIGN KEY (`id`) REFERENCES `userLogin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (24,'小明','男','2017-10-03','2017-11-03',80),(39,'小红','男','1995-01-10','2017-11-05',0),(40,'小林','女','1990-06-01','2017-11-05',0);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

LOCK TABLES `student_course` WRITE;
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES (1,24,0,NULL,0),(4,24,18,10,1);
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL COMMENT '姓名',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `degree` varchar(20) DEFAULT NULL COMMENT '学历',
  `register_time` date DEFAULT NULL COMMENT '注册日期',
  PRIMARY KEY (`id`),
  CONSTRAINT `teacher_userlogin_FK` FOREIGN KEY (`id`) REFERENCES `userLogin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (25,'李老师','男','2000-11-03','本科','2017-11-03'),(26,'张老师','女','2017-11-03','研究生','2017-11-03'),(37,'刘老师','男','1997-11-13','硕士','2017-11-05'),(38,'曹老师','男','1958-11-12','博士','2017-11-05');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userLogin`
--

DROP TABLE IF EXISTS `userLogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userLogin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL COMMENT '姓名',
  `password` char(56) NOT NULL COMMENT '密码',
  `role` int(2) NOT NULL DEFAULT '2' COMMENT '角色权限',
  PRIMARY KEY (`id`),
  KEY `role` (`role`),
  CONSTRAINT `userLogin_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userLogin`
--

LOCK TABLES `userLogin` WRITE;
/*!40000 ALTER TABLE `userLogin` DISABLE KEYS */;
INSERT INTO `userLogin` VALUES (23,'admin','b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d',1),(24,'小明','451ff30ebcb01769df3e7cc34a2992851d845c6ba57487a07431a444',3),(25,'李老师','b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d',2),(26,'张老师','b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d',2),(37,'刘老师','fea9f33849d17828ad3ce265fc4864e7a4c32639308378b80a5299f6',2),(38,'曹老师','c6de687492e0575e31313502c06a81c4c1bb04bfa1f2d6b7f6eae3e8',2),(39,'小红','19add14d4dda0a714c84bb6cf9575b4f7ab424f50bfadfc715ec7b29',3),(40,'小林','59561b7cad4bb78db39cd9e82538fc0f09c09f884ccb2da37903b586',3);
/*!40000 ALTER TABLE `userLogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'online_study_system'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-05 16:14:23
