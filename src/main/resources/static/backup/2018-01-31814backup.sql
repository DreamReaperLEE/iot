-- MySQL dump 10.13  Distrib 5.5.53, for Win32 (AMD64)
--
-- Host: localhost    Database: ceats
-- ------------------------------------------------------
-- Server version	5.5.53

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
-- Table structure for table `co_direct`
--

DROP TABLE IF EXISTS `co_direct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `co_direct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `direct_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `co_direct`
--

LOCK TABLES `co_direct` WRITE;
/*!40000 ALTER TABLE `co_direct` DISABLE KEYS */;
INSERT INTO `co_direct` VALUES (1,'数据库'),(2,'防火墙'),(3,'文档');
/*!40000 ALTER TABLE `co_direct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `co_type`
--

DROP TABLE IF EXISTS `co_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `co_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `co_type`
--

LOCK TABLES `co_type` WRITE;
/*!40000 ALTER TABLE `co_type` DISABLE KEYS */;
INSERT INTO `co_type` VALUES (1,'入门'),(2,'进阶'),(3,'高级');
/*!40000 ALTER TABLE `co_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  `cdetail` varchar(255) DEFAULT NULL,
  `cintroduce` varchar(255) DEFAULT NULL,
  `tid` int(11) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `ctype` varchar(255) DEFAULT NULL,
  `cdirect` varchar(255) DEFAULT NULL,
  `cpic` varchar(255) DEFAULT NULL,
  `cpoint` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'数据结构','数据结构是计算机存储、组织数据的方式。数据结构是指相互之间存在一种或多种特定关系的数据元素的集合。','数据结构是计算机存储、组织数据的方式。数据结构是指相互之间存在一种或多种特定关系的数据元素的集合。',2013201311,'2017-12-10','1','1','/pic/20180124090511889DSC_00433115.jpg','通常情况下，精心选择的数据结构可以带来更高的运行或者存储效率。数据结构往往同高效的检索算法和索引技术有关。'),(6,'啊手动阀手动阀撒旦发','啊手动阀手动阀',NULL,2013201311,'2018-01-23','1','1','/pic/20180123153035992IMG_2444.JPG',NULL);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emploee`
--

DROP TABLE IF EXISTS `emploee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emploee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `priv` int(11) DEFAULT '0',
  `email` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2013201400 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emploee`
--

LOCK TABLES `emploee` WRITE;
/*!40000 ALTER TABLE `emploee` DISABLE KEYS */;
INSERT INTO `emploee` VALUES (2013201308,'李守政','d224bf2035155c246e0ba06d310d72ca',2,'26407166@qq.com','13251509667','/pic/20180123150024204IMG_2444.JPG','后端工程师',1),(2013201309,'淡定淡定',NULL,2,'26407166@qq.com','13251509667',NULL,'nullsdfsdfs',1),(2013201310,'李守清1','d224bf2035155c246e0ba06d310d72ca',0,'26407166@qq.com1','132515096671','/pic/1.jpg','后端工程师1',1),(2013201311,'测试用例','d224bf2035155c246e0ba06d310d72ca',1,'26407166@qq.com','13251509667','/pic/1.jpg','垡dd',1),(2013201399,'22262',NULL,1,'26407166@qq.com','13251509667','20180116163121790wave-2649372_1920 (1).jpg','aa',1),(2013201398,'22262','1a6ca3e69632e4e0c7df5aece3ccb582',1,'26407166@qq.com','13251509667','/pic/20180116163606991wave-2649372_1920.jpg','dd',1),(2013201397,'22262','bb4fc04476d812e05149813ef74a1173',2,'26407166@qq.com','13251509667','/pic/20180116164454276wave-2649372_1920.jpg','aa',1),(2013201356,'esdf','d8f028e4efd6f3a6519cc5266227c1e2',1,'26407166@qq.com','13251509667','/pic/20180118162722352wave-2649372_1920 (1).jpg','ddd',1),(2013201389,'2013201389','0b061dfa5e655b5529a4a9949b5956c5',2,'26407166@qq.com','13251509667',NULL,NULL,1);
/*!40000 ALTER TABLE `emploee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tid` varchar(255) DEFAULT NULL,
  `edesc` varchar(255) DEFAULT NULL,
  `epic` varchar(255) DEFAULT NULL,
  `ename` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `etime` varchar(255) DEFAULT NULL,
  `stime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (4,'2013201311','这是一门计算机网络考试1','/pic/data.jpg','计算机网络1','2018-01-24','23:01','08:01');
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,2013201308,'我有意见','2018011516145278987D1935D6FFDFA1777CF4F847C45A758544C6826.txt','2018-01-15 16:14:52'),(2,2013201308,'我有一件',NULL,'2018-01-15 16:15:16');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hierarchy`
--

DROP TABLE IF EXISTS `hierarchy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hierarchy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hierarchy`
--

LOCK TABLES `hierarchy` WRITE;
/*!40000 ALTER TABLE `hierarchy` DISABLE KEYS */;
INSERT INTO `hierarchy` VALUES (1,'第一个','/pic/1.jpg','第一个介绍','[{\"topic\":\"第一层\",\"introduce\":\"第一层介绍\",\"intList\":[1,2]},{\"topic\":\"第二层\",\"introduce\":\"第二层介绍\",\"intList\":[1,2]}]'),(2,'第二个','/pic/1.jpg','第二个介绍','[{\"topic\":\"第二层\",\"introduce\":\"第二层介绍\",\"intList\":[1,2]},{\"topic\":\"第二层\",\"introduce\":\"第二层介绍\",\"intList\":[1,2]}]');
/*!40000 ALTER TABLE `hierarchy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homepage`
--

DROP TABLE IF EXISTS `homepage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `homepage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `other` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homepage`
--

LOCK TABLES `homepage` WRITE;
/*!40000 ALTER TABLE `homepage` DISABLE KEYS */;
INSERT INTO `homepage` VALUES (1,2,2,'/pic/1.jpg'),(2,2,1,'[\"2013201311\",\"2013201311\"]'),(3,0,0,'[\"1\"]');
/*!40000 ALTER TABLE `homepage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inform`
--

DROP TABLE IF EXISTS `inform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iid` int(11) DEFAULT NULL,
  `topic` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inform`
--

LOCK TABLES `inform` WRITE;
/*!40000 ALTER TABLE `inform` DISABLE KEYS */;
INSERT INTO `inform` VALUES (1,2013201308,'水声工程团队入选教育部首批“全国高校黄大年式教师团队”','经过高校自主申报、省级教育行政部门遴选推荐、专家评审等环节，水声工程团队凭借其在师德师风、教育教学、科研创新、社会服务、团队建设等方面取得的突出成果和工作实绩入选。党的十八大以来，该团队培养硕士研究生642人、博士研究生136人；编写教材6本、教学案例2项。团队科研项目获省部级以上奖项23项，教学成果获省部级以上奖项6项，团队及成员获省部级以上表彰23项。团队重要科研立项项目3.1亿元，其中，1000万以上项目4项。','[\"/pic/1.jpg\",\"/pic/1.jpg\"]','2017-12-27 08:00:00',NULL,1,1),(2,2013201308,'助“深海勇士”号“大海捞针”','由教育部科学技术委员会组织评选的2017年度“中国高等学校十大科技进展”日前在京揭晓，我校水声学院孙大军教授主持完成的“深海高精度水声综合定位技术”跻身十强，是海洋工程领域唯一入选项目，标志着我国在深海高精度水声定位研究达到国际领先水平。','[\"/pic/1.jpg\",\"/pic/1.jpg\"]','2017-12-25 08:00:00','/pic/1.jpg',0,1);
/*!40000 ALTER TABLE `inform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) NOT NULL DEFAULT '0',
  `detail` varchar(255) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
INSERT INTO `paper` VALUES (1,4,'{\"info\":[\"1\",\"2\",\"3\"],\"level\":\"5\"}','试卷答案',5),(2,2,'试卷详细信息','试卷答案',5);
/*!40000 ALTER TABLE `paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `more` int(11) DEFAULT NULL,
  `digital` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,1,'不','解:(1)∠BDA′=2∠A;根据折叠的性质可知∠DA′E=∠A,∠DA′E+∠A=∠BDA′,故∠BDA′=2∠A;(2)∠BDA′+∠CEA′=2∠A,理由:在四边形ADA...',1,'/pic/IMG_20171001_120704.jpg',1,'{\"choice\":[\"240\",\"360\"],\"answer\":[\"0\"]}',5),(2,1,NULL,'栈和队列的共同特点是',1,'/pic/IMG_20171001_120704.jpg',2,'{\"choice\":[\"只允许在端点处插入和删除元素\",\"都是先进后出\",\"都是先进先出\",\"没有共同点\"],\"answer\":[\"0\",\"1\"]}',3),(3,1,NULL,'下述哪一个电缆类型支持最大的电缆长度粗同轴电缆',0,NULL,0,'{\"choice\":[\"hello\",\"word\"],\"answer\":[\"1\"]}',4);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `lid` int(11) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` VALUES (1,2013201308,1,1,'2017-01-01 08:00:00'),(2,2013201308,2,1,'2017-01-01 08:00:00');
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL DEFAULT '0',
  `pid` int(11) NOT NULL DEFAULT '0',
  `detail` varchar(255) DEFAULT NULL,
  `score` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (16,2013201308,1,'[[\"1\"],[\"2\",\"3\"],[\"0\"]]',-1);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `source`
--

DROP TABLE IF EXISTS `source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `source` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL,
  `lesson` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `topic` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `source`
--

LOCK TABLES `source` WRITE;
/*!40000 ALTER TABLE `source` DISABLE KEYS */;
INSERT INTO `source` VALUES (1,1,1,0,'数据结构基础','哈哈哈哈哈哈哈哈'),(2,1,2,0,'计算机网络基础','顶顶顶顶顶顶'),(3,1,1,1,'课程概述','    通过本课程的学习，使学生深透地理解数据结构的逻辑结构和物理结构的基本概念以及有关算法，培养基本的、良好的程序设计技能，编制高效可靠的程序，为学习操作系统、编译原理和数据库等课程奠定基础。\r\n    通过本课程的学习，使学生深透地理解数据结构的逻辑结构和物理结构的基本概念以及有关算法，培养基本的、良好的程序设计技能，编制高效可靠的程序，为学习操作系统、编译原理和数据库等课程奠定基础。\r\n    通过本课程的学习，使学生深透地理解数据结构的逻辑结构和物理结构的基本概念以及有关算法，培养基本的、良好的程'),(4,1,1,2,'路径图','/pic/get.JPG');
/*!40000 ALTER TABLE `source` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-31 15:09:14
