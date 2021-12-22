CREATE DATABASE IF NOT EXISTS `fog`;

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
-- Table structure for table `CarportMaterialMisc`
--

DROP TABLE IF EXISTS `CarportMaterialMisc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CarportMaterialMisc` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CarportMaterials`
--

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
-- Table structure for table `CarportMaterials`
--

DROP TABLE IF EXISTS `CarportMaterials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CarportMaterials` (
                                    `material_id` int NOT NULL AUTO_INCREMENT,
                                    `material_name` varchar(100) NOT NULL,
                                    `material_price` double NOT NULL,
                                    `material_length` int DEFAULT NULL,
                                    `nickname` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                    PRIMARY KEY (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CarportMaterials`
--

LOCK TABLES `CarportMaterials` WRITE;
/*!40000 ALTER TABLE `CarportMaterials` DISABLE KEYS */;
INSERT INTO `CarportMaterials` VALUES (1,'PLASTMO SKRUER (BUNDSKRUNING) T/TRAPEZ ÆSKE A 200 STK',359,NULL,''),(2,'PASLODE HULBÅND TYPE 20-1.5 20X1,5MM VFZ RULLE Á 10 MTR.',379,NULL,'holetape'),(3,'PASLODE UNIVERSALBESLAG 190MM HØJRE VARMFORZINKET.',30,NULL,'universalR'),(4,'PASLODE UNIVERSALBESLAG 190MM VENSTRE VARMFORZINKET F2106.',30,NULL,'universalL'),(5,'NKT SPUN+ SKRUE UH 4,5X25MM TX20 ELF. PAKKE Á 200 STK',169,60,''),(6,'NKT SPUN+ SKRUE 4,0X50/35MM UH TX20 ELF. PAKKE Á 200 STK',139,50,''),(7,'NKT BRÆDDEBOLT VARMFORZINKET M10X120 MM 25 STK/PK',329,120,''),(8,'FIRKANTSKIVER VFZ 10MM 40X40X11X4MM 50 STK/PK',339,NULL,''),(9,'FOG OUTDOOR SKRUE 4,5X70MM UH UDE 400STK TX20',199,70,''),(10,'NKT BASIC SKRUE 4,5X50MM UH UDE 300STK. TX20',109,50,''),(11,'STALDDØRSGREB GALV 50-75MM DØRTYKKELSE TIL HÆNGELÅS',249,NULL,''),(12,'placeholder t-hængsel',1,NULL,''),(13,'PASLODE VINKELBESLAG S35PL 50X50X1,5X35MM MED RIB VARMFORZINKET.',6.949999809265137,NULL,''),(14,'25X200 MM VTA TRYKIMPRÆGNERET NTR/AB 300 CM 70% PEFC™',259.03,360,''),(15,'25X200 MM VTA TRYKIMPRÆGNERET NTR/AB 540 CM 70% PEFC™',388.53,540,''),(16,'25X125 MM STERN OVERBRÆDT TRYKIMPRÆGNERET GRAN NTR/AB 4019 - FÆRDIGMÅL 22X110 MM 70% PEFC™',163.59,420,''),(17,'25X125 MM STERN OVERBRÆDT TRYKIMPRÆGNERET GRAN NTR/AB 4019 - FÆRDIGMÅL 22X110 MM 70% PEFC™',210.33,540,''),(18,'38X73 MM C18 TAGLÆGTER EGAL. 420 CM - 70% PEFC™',134.19,420,''),(19,'45X95 MM FYR SEKSTA TRYKIMPRÆGNERET NTR/AB HVL.4S M/RUNDING 300 CM 70% PEFC™',149.85,300,''),(20,'45X95 MM FYR SEKSTA TRYKIMPRÆGNERET NTR/AB HVL.4S M/RUNDING 300 CM 70% PEFC™',119.88,240,''),(21,'47X200 MM SPÆRTRÆ C24 HØVLET TIL 45X195MM 300CM - 70% PEFC™',894,600,''),(22,'47X200 MM SPÆRTRÆ C24 HØVLET TIL 45X195MM 300CM - 70% PEFC™',508.8,480,''),(23,'97X97 MM FULDKANTET FYR IMPR. NTR/A TRYKIMPRÆGNERET NTR/A 180 CM - 70% PEFC™',248.85,300,''),(24,'19X100 MM BRÆDDER FYR SAVSKÅRET U/S - 100% PEFC™ CERTIFICERET',83.9,210,''),(25,'19X100 MM BRÆDDER FYR SAVSKÅRET U/S - 100% PEFC™ CERTIFICERET [PLACEHOLDER]',170,540,''),(26,'19X100 MM BRÆDDER FYR SAVSKÅRET U/S - 100% PEFC™ CERTIFICERET',143.83,360,''),(27,'TRAPEZPLADE BLÅTONET 109X240CM PLASTMO ECOLITE TAGPLADE PVC',284,600,'Blåtonet Trapezplade'),(28,'TRAPEZPLADE BLÅTONET 109X240CM PLASTMO ECOLITE TAGPLADE PVC',204,420,'Blåtonet Trapezplade ');
/*!40000 ALTER TABLE `CarportMaterials` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

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
-- Table structure for table `CarportValues`
--

DROP TABLE IF EXISTS `CarportValues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CarportValues` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `length` int DEFAULT NULL,
                                 `width` int DEFAULT NULL,
                                 `shed_length` int DEFAULT NULL,
                                 `shed_width` int DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CarportValues`
--

LOCK TABLES `CarportValues` WRITE;
/*!40000 ALTER TABLE `CarportValues` DISABLE KEYS */;
INSERT INTO `CarportValues` VALUES (1,240,240,240,240),(2,270,270,270,270),(3,300,300,300,300),(4,330,330,330,330),(5,360,360,360,360),(6,390,390,390,390),(7,420,420,420,420),(8,450,450,450,450),(9,480,480,480,480),(10,510,510,510,510),(11,540,540,540,540),(12,570,570,570,570),(13,600,600,600,600),(14,630,NULL,630,630),(15,660,NULL,660,660),(16,690,NULL,690,690),(17,720,NULL,720,720),(18,750,NULL,NULL,750),(19,780,NULL,NULL,NULL);
/*!40000 ALTER TABLE `CarportValues` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

--
-- Table structure for table `CarportMeterialForRequest`
--

DROP TABLE IF EXISTS `CarportMeterialForRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CarportMeterialForRequest` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CarportRequest`
--

DROP TABLE IF EXISTS `CarportRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CarportRequest` (
  `id_carportRequest` int NOT NULL AUTO_INCREMENT,
  `width` int NOT NULL,
  `length` int NOT NULL,
  `id_roof` int NOT NULL,
  `hasShed` tinyint(1) NOT NULL,
  `shedWidth` int DEFAULT NULL,
  `shedLength` int DEFAULT NULL,
  PRIMARY KEY (`id_carportRequest`),
  KEY `CarportRequest_FK` (`id_roof`),
  CONSTRAINT `CarportRequest_FK` FOREIGN KEY (`id_roof`) REFERENCES `CarportMaterials` (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `id_order` int NOT NULL AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `id_carportRequest` int NOT NULL,
  `status` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_danish_ci DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_order`),
  KEY `Orders_FK` (`id_user`),
  KEY `id_Ordersitems_FK` (`id_carportRequest`),
  CONSTRAINT `id_Ordersitems_FK` FOREIGN KEY (`id_carportRequest`) REFERENCES `CarportRequest` (`id_carportRequest`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Orders_FK` FOREIGN KEY (`id_user`) REFERENCES `Users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sessionID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `fname` varchar(225) COLLATE utf8mb4_danish_ci NOT NULL,
  `lname` varchar(255) COLLATE utf8mb4_danish_ci NOT NULL,
  `address` varchar(256) COLLATE utf8mb4_danish_ci DEFAULT NULL,
  `zipcode` int DEFAULT NULL,
  `city` varchar(256) COLLATE utf8mb4_danish_ci DEFAULT NULL,
  `phone` varchar(256) COLLATE utf8mb4_danish_ci DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'fog'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;