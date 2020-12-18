-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: curriculum_design
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_browser`
--

DROP TABLE IF EXISTS `t_browser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_browser` (
  `b_id` int NOT NULL AUTO_INCREMENT,
  `browser` varchar(255) NOT NULL,
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_browser`
--

LOCK TABLES `t_browser` WRITE;
/*!40000 ALTER TABLE `t_browser` DISABLE KEYS */;
INSERT INTO `t_browser` (`b_id`, `browser`) VALUES (1,'Chrome'),(2,'Safari'),(3,'Firefox'),(4,'IE'),(5,'Opera'),(6,'其他');
/*!40000 ALTER TABLE `t_browser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_device`
--

DROP TABLE IF EXISTS `t_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_device` (
  `d_id` int NOT NULL AUTO_INCREMENT,
  `device` varchar(255) NOT NULL,
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_device`
--

LOCK TABLES `t_device` WRITE;
/*!40000 ALTER TABLE `t_device` DISABLE KEYS */;
INSERT INTO `t_device` (`d_id`, `device`) VALUES (1,'电脑PC'),(2,'Android'),(3,'IOS'),(4,'其他');
/*!40000 ALTER TABLE `t_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_login`
--

DROP TABLE IF EXISTS `t_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_login` (
  `id` int NOT NULL AUTO_INCREMENT,
  `IpAddress` varchar(255) NOT NULL,
  `b_id` int NOT NULL,
  `LoginMethod` varchar(255) NOT NULL,
  `d_id` int NOT NULL,
  `Time` datetime NOT NULL,
  `userName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `t_login_t_browser_b_id_fk` (`b_id`),
  KEY `t_login_t_device_d_id_fk` (`d_id`),
  KEY `t_login_t_user_userName_fk` (`userName`),
  CONSTRAINT `t_login_t_browser_b_id_fk` FOREIGN KEY (`b_id`) REFERENCES `t_browser` (`b_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_login_t_device_d_id_fk` FOREIGN KEY (`d_id`) REFERENCES `t_device` (`d_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_login_t_user_userName_fk` FOREIGN KEY (`userName`) REFERENCES `t_user` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_login`
--

LOCK TABLES `t_login` WRITE;
/*!40000 ALTER TABLE `t_login` DISABLE KEYS */;
INSERT INTO `t_login` (`id`, `IpAddress`, `b_id`, `LoginMethod`, `d_id`, `Time`, `userName`) VALUES (53,'10.181.184.67',1,'暂无数据',1,'2020-12-17 15:15:04','admin'),(54,'10.181.184.67',1,'暂无数据',1,'2020-12-17 15:56:46','admin'),(68,'10.181.184.67',1,'暂无数据',1,'2020-12-17 19:58:33','admin'),(69,'10.181.184.67',1,'暂无数据',1,'2020-12-17 19:59:57','admin'),(70,'10.181.184.67',1,'暂无数据',1,'2020-12-17 20:01:23','admin'),(71,'10.181.184.67',1,'暂无数据',1,'2020-12-17 20:11:01','admin'),(72,'10.181.184.67',1,'暂无数据',1,'2020-12-17 20:15:16','admin'),(73,'10.181.184.67',1,'暂无数据',1,'2020-12-17 20:28:47','admin'),(74,'10.181.184.67',1,'暂无数据',1,'2020-12-17 21:12:08','admin'),(75,'10.181.184.67',1,'暂无数据',1,'2020-12-17 21:17:12','admin'),(76,'10.181.184.67',1,'暂无数据',1,'2020-12-17 21:21:29','admin'),(77,'10.181.184.67',1,'暂无数据',1,'2020-12-17 21:23:28','admin'),(78,'10.181.184.67',1,'暂无数据',1,'2020-12-17 21:25:55','admin'),(79,'10.181.184.67',1,'暂无数据',1,'2020-12-17 21:30:49','admin'),(80,'10.181.184.67',1,'暂无数据',1,'2020-12-17 21:33:05','admin'),(81,'10.181.184.67',1,'暂无数据',1,'2020-12-17 21:38:34','admin'),(82,'10.181.184.67',1,'暂无数据',1,'2020-12-17 22:16:03','admin'),(83,'10.181.184.67',1,'暂无数据',1,'2020-12-17 22:30:17','admin'),(84,'10.181.184.67',1,'暂无数据',1,'2020-12-17 22:34:45','admin'),(85,'10.181.184.67',1,'暂无数据',1,'2020-12-17 22:37:46','admin'),(86,'10.181.184.67',1,'暂无数据',1,'2020-12-17 22:40:28','admin'),(87,'10.181.184.67',1,'暂无数据',1,'2020-12-17 22:43:05','admin'),(88,'10.181.184.67',1,'暂无数据',1,'2020-12-17 22:45:24','admin'),(89,'10.181.184.67',1,'暂无数据',1,'2020-12-17 22:47:59','admin'),(90,'10.181.184.67',1,'暂无数据',1,'2020-12-17 22:55:46','admin'),(91,'10.181.184.67',1,'暂无数据',1,'2020-12-17 23:00:00','admin'),(92,'10.181.184.67',1,'暂无数据',1,'2020-12-17 23:04:53','admin'),(93,'10.181.184.67',1,'暂无数据',1,'2020-12-17 23:09:31','admin'),(94,'10.181.184.67',1,'暂无数据',1,'2020-12-17 23:10:30','admin'),(95,'10.181.184.67',1,'暂无数据',1,'2020-12-17 23:15:27','admin'),(96,'10.181.184.67',1,'暂无数据',1,'2020-12-18 12:01:06','admin'),(97,'10.181.184.67',1,'暂无数据',2,'2020-12-18 12:06:11','admin'),(98,'10.181.184.67',1,'暂无数据',1,'2020-12-18 12:06:30','admin'),(99,'10.181.184.67',3,'暂无数据',1,'2020-12-18 12:07:06','admin'),(100,'10.181.184.67',1,'暂无数据',1,'2020-12-18 12:27:58','admin'),(101,'10.181.184.67',1,'暂无数据',1,'2020-12-18 13:10:00','admin'),(102,'10.181.184.67',1,'暂无数据',1,'2020-12-18 13:17:00','admin'),(103,'10.181.184.67',1,'暂无数据',1,'2020-12-18 14:56:06','admin'),(104,'10.181.184.67',1,'暂无数据',1,'2020-12-18 14:58:30','admin'),(105,'10.181.184.67',1,'暂无数据',1,'2020-12-18 15:06:06','admin'),(106,'10.181.184.67',1,'暂无数据',1,'2020-12-18 15:07:43','admin'),(107,'10.181.184.67',1,'暂无数据',1,'2020-12-18 15:10:31','admin'),(108,'10.181.184.67',1,'暂无数据',1,'2020-12-18 15:13:39','admin'),(109,'10.181.184.67',1,'暂无数据',1,'2020-12-18 15:33:03','admin'),(110,'10.181.184.67',1,'暂无数据',1,'2020-12-18 16:14:02','admin'),(111,'10.181.184.67',1,'暂无数据',1,'2020-12-18 16:27:45','admin'),(112,'10.181.184.67',1,'暂无数据',1,'2020-12-18 16:32:01','admin'),(113,'10.181.184.67',1,'暂无数据',1,'2020-12-18 16:33:48','admin'),(114,'10.181.184.67',1,'暂无数据',1,'2020-12-18 16:39:50','admin'),(115,'10.181.184.67',1,'暂无数据',1,'2020-12-18 17:08:48','admin');
/*!40000 ALTER TABLE `t_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role` (
  `roleId` int NOT NULL,
  `roleName` varchar(255) NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` (`roleId`, `roleName`) VALUES (1,'管理员'),(2,'普通用户');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `chrName` varchar(255) NOT NULL,
  `emailAddress` varchar(255) NOT NULL,
  `userPhone` varchar(255) NOT NULL,
  `imgAddress` varchar(255) NOT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`userName`, `password`, `chrName`, `emailAddress`, `userPhone`, `imgAddress`) VALUES ('admin','e10adc3949ba59abbe56e057f20f883e','管理员','318185326@qq.com','13545672130','/images/userico/7a82c9fa-8c68-4666-a187-2e274246ae7b.png');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `roleId` int NOT NULL,
  `userName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `t_user_role_t_role_roleId_fk` (`roleId`),
  KEY `t_user_role_t_user_userName_fk` (`userName`),
  CONSTRAINT `t_user_role_t_role_roleId_fk` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_user_role_t_user_userName_fk` FOREIGN KEY (`userName`) REFERENCES `t_user` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-18 17:49:01
