CREATE DATABASE
IF NOT EXISTS `cupcake` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_danish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- cupcake.Bottom definition

DROP TABLE IF EXISTS `Bottom`;
CREATE TABLE `Bottom`
(
  `id_bottom` int NOT NULL AUTO_INCREMENT,
  `name` varchar
(100) CHARACTER
SET utf8mb4
COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `bottomPrice` double DEFAULT NULL,
  `desc` varchar
(255) CHARACTER
SET utf8mb4
COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY
(`id_bottom`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- cupcake.Cart definition

DROP TABLE IF EXISTS `Cart`;
CREATE TABLE `Cart`
(
  `id_cart` int NOT NULL AUTO_INCREMENT,
  `id_cartitems` int NOT NULL,
  `id_user` int NOT NULL,
  PRIMARY KEY
(`id_cart`),
  KEY `Cart_FK`
(`id_cartitems`),
  CONSTRAINT `Cart_FK` FOREIGN KEY
(`id_cartitems`) REFERENCES `Cartitems`
(`id_cartitems`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- cupcake.Cartitems definition

DROP TABLE IF EXISTS `Cartitems`;
CREATE TABLE `Cartitems`
(
  `id_cartitems` int NOT NULL AUTO_INCREMENT,
  `id_bottom` int NOT NULL,
  `id_topping` int NOT NULL,
  `id_user` int NOT NULL,
  `amount` int NOT NULL,
  PRIMARY KEY
(`id_cartitems`),
  KEY `Bottom_FK`
(`id_bottom`),
  KEY `Topping_FK`
(`id_topping`),
  KEY `USER_FK`
(`id_user`),
  CONSTRAINT `Bottom_FK` FOREIGN KEY
(`id_bottom`) REFERENCES `Bottom`
(`id_bottom`),
  CONSTRAINT `Topping_FK` FOREIGN KEY
(`id_topping`) REFERENCES `Topping`
(`id_topping`),
  CONSTRAINT `USER_FK` FOREIGN KEY
(`id_user`) REFERENCES `Users`
(`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- cupcake.Orderitems definition
DROP TABLE IF EXISTS `Orderitems`;
CREATE TABLE `Orderitems`
(
  `id_orderitems` int NOT NULL AUTO_INCREMENT,
  `id_bottom` int NOT NULL,
  `id_topping` int NOT NULL,
  `amount` int NOT NULL,
  PRIMARY KEY
(`id_orderitems`),
  KEY `Bottom_Orderitems_FK`
(`id_bottom`),
  KEY `Topping_Orderitems_FK`
(`id_topping`),
  CONSTRAINT `Bottom_Orderitems_FK` FOREIGN KEY
(`id_bottom`) REFERENCES `Bottom`
(`id_bottom`),
  CONSTRAINT `Topping_Orderitems_FK` FOREIGN KEY
(`id_topping`) REFERENCES `Topping`
(`id_topping`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- cupcake.Orders definition

DROP TABLE IF EXISTS `Orders`;
CREATE TABLE `Orders`
(
  `id_order` int NOT NULL AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `id_orderitems` int NOT NULL,
  `status` varchar
(100) COLLATE utf8mb4_danish_ci DEFAULT NULL,
  PRIMARY KEY
(`id_order`),
  KEY `Orders_FK`
(`id_user`),
  KEY `id_Ordersitems_FK`
(`id_orderitems`),
  CONSTRAINT `id_Ordersitems_FK` FOREIGN KEY
(`id_orderitems`) REFERENCES `Orderitems`
(`id_orderitems`) ON
DELETE CASCADE ON
UPDATE CASCADE,
  CONSTRAINT `Orders_FK` FOREIGN KEY
(`id_user`) REFERENCES `Users`
(`id_user`) ON
DELETE CASCADE ON
UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET
=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- cupcake.PremadeCupcake definition

DROP TABLE IF EXISTS `PremadeCupcake`;
CREATE TABLE `PremadeCupcake`
(
  `id_item` int NOT NULL AUTO_INCREMENT,
  `name` varchar
(100) NOT NULL,
  `desc` varchar
(255) NOT NULL,
  `imageURL` varchar
(100) NOT NULL,
  `bottom` int DEFAULT NULL,
  `topping` int DEFAULT NULL,
  PRIMARY KEY
(`id_item`),
  KEY `ItemDescriptions_FK`
(`bottom`),
  KEY `ItemDescriptions_FK_1`
(`topping`),
  CONSTRAINT `ItemDescriptions_FK` FOREIGN KEY
(`bottom`) REFERENCES `Bottom`
(`id_bottom`),
  CONSTRAINT `ItemDescriptions_FK_1` FOREIGN KEY
(`topping`) REFERENCES `Topping`
(`id_topping`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- cupcake.Topping definition

DROP TABLE IF EXISTS `Topping`;
CREATE TABLE `Topping`
(
  `id_topping` int NOT NULL AUTO_INCREMENT,
  `name` varchar
(100) CHARACTER
SET utf8mb4
COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `toppingPrice` double DEFAULT NULL,
  `desc` varchar
(255) CHARACTER
SET utf8mb4
COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY
(`id_topping`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- cupcake.Users definition


DROP TABLE IF EXISTS `Users`;
CREATE TABLE `Users`
(
  `id_user` int NOT NULL AUTO_INCREMENT,
  `email` varchar
(100) CHARACTER
SET utf8mb4
COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar
(256) CHARACTER
SET utf8mb4
COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `balance` double NOT NULL,
  `role` varchar
(100) CHARACTER
SET utf8mb4
COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sessionID` varchar
(100) CHARACTER
SET utf8mb4
COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY
(`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;