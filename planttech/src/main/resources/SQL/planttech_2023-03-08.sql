# ************************************************************
# Sequel Ace SQL dump
# Version 20046
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.9.4-MariaDB)
# Database: planttech
# Generation Time: 2023-03-08 06:56:35 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table DHT11
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DHT11`;

CREATE TABLE `DHT11` (
  `SEQ` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `DATETIME` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `TEMPERATURE` tinyint(11) DEFAULT NULL,
  `HUMIDITY` tinyint(3) NOT NULL,
  `HI` decimal(4,2) NOT NULL,
  PRIMARY KEY (`SEQ`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `DHT11` WRITE;
/*!40000 ALTER TABLE `DHT11` DISABLE KEYS */;

INSERT INTO `DHT11` (`SEQ`, `DATETIME`, `TEMPERATURE`, `HUMIDITY`, `HI`)
VALUES
	(1,'2023-02-06 17:47:42',48,89,88.28),
	(2,'2023-02-15 08:28:59',38,8,76.33),
	(3,'2023-02-09 07:54:37',35,70,18.32),
	(4,'2023-02-12 03:07:31',25,6,68.21),
	(5,'2023-02-15 09:29:51',9,84,51.57),
	(6,'2023-02-10 17:06:21',45,42,93.92),
	(7,'2023-02-24 06:10:35',11,91,1.56),
	(8,'2023-03-04 06:46:10',19,20,2.93),
	(9,'2023-02-25 22:27:05',32,86,11.31),
	(10,'2023-02-18 14:46:14',13,0,67.86),
	(11,'2023-02-19 15:47:00',23,52,98.76),
	(12,'2023-02-08 20:21:27',28,87,70.95),
	(13,'2023-02-11 01:48:35',45,49,72.20),
	(14,'2023-02-23 15:04:45',19,84,52.14),
	(15,'2023-02-16 23:14:36',-7,98,43.64),
	(16,'2023-02-11 09:24:06',2,24,59.64),
	(17,'2023-02-26 02:25:05',-6,79,2.46),
	(18,'2023-02-05 14:35:47',46,30,94.92),
	(19,'2023-02-21 07:50:19',45,91,27.44),
	(20,'2023-02-11 12:39:05',-6,68,66.00),
	(21,'2023-02-23 19:51:26',36,50,72.96),
	(22,'2023-02-08 06:19:11',22,32,56.25),
	(23,'2023-02-17 15:26:19',14,51,88.77),
	(24,'2023-02-18 10:25:31',8,5,75.67),
	(25,'2023-02-19 03:48:31',-4,40,10.41),
	(26,'2023-02-08 14:46:30',-6,0,87.84),
	(27,'2023-02-18 12:04:06',45,35,45.86),
	(28,'2023-02-20 05:59:46',25,87,9.33),
	(29,'2023-02-10 20:06:41',-4,37,46.35),
	(30,'2023-03-03 20:15:51',3,69,80.26),
	(31,'2023-02-18 21:55:54',48,24,86.07),
	(32,'2023-03-01 02:09:21',-7,1,50.83),
	(33,'2023-03-05 16:33:32',29,15,98.39),
	(34,'2023-02-22 21:05:50',43,89,9.07),
	(35,'2023-02-20 19:39:32',31,53,41.30),
	(36,'2023-02-05 17:09:25',12,11,80.98),
	(37,'2023-02-06 16:19:18',42,39,15.72),
	(38,'2023-02-14 15:27:47',2,21,67.02),
	(39,'2023-02-12 21:39:36',37,67,13.35),
	(40,'2023-03-04 11:24:41',41,44,27.49),
	(41,'2023-02-27 18:36:48',28,76,19.98),
	(42,'2023-02-27 16:55:36',37,90,41.81),
	(43,'2023-02-19 21:14:37',42,42,7.08),
	(44,'2023-02-24 22:11:53',21,42,53.99),
	(45,'2023-03-01 02:12:15',47,30,36.12),
	(46,'2023-02-15 16:54:13',4,42,24.56),
	(47,'2023-02-11 03:30:16',20,4,98.54),
	(48,'2023-02-16 01:26:30',39,73,49.12),
	(49,'2023-02-17 05:19:49',24,32,77.21),
	(50,'2023-02-28 21:07:43',17,20,86.67),
	(51,'2023-02-06 21:26:07',38,57,86.80),
	(52,'2023-02-18 20:25:13',1,43,36.99),
	(53,'2023-03-05 13:38:33',39,52,50.84),
	(54,'2023-02-16 05:28:54',21,8,12.62),
	(55,'2023-02-19 19:12:50',36,70,88.67),
	(56,'2023-02-25 01:32:58',26,40,19.84),
	(57,'2023-02-25 17:06:11',22,15,57.24),
	(58,'2023-02-22 05:37:52',45,48,27.38),
	(59,'2023-02-18 11:17:39',-3,7,48.63),
	(60,'2023-02-03 02:38:19',5,39,49.80),
	(61,'2023-03-01 22:16:32',10,77,68.70),
	(62,'2023-02-28 19:16:24',-7,38,90.20),
	(63,'2023-02-27 06:59:11',45,50,97.05),
	(64,'2023-02-19 23:01:51',-7,19,63.42),
	(65,'2023-02-16 02:24:27',-2,5,68.73),
	(66,'2023-02-07 00:23:17',47,63,7.03),
	(67,'2023-02-17 18:22:16',42,83,1.01),
	(68,'2023-02-19 03:59:14',40,88,20.12),
	(69,'2023-02-25 19:06:04',1,91,4.95),
	(70,'2023-02-12 05:15:25',-6,1,65.86),
	(71,'2023-02-19 14:50:02',28,46,43.70),
	(72,'2023-02-17 02:13:10',-4,86,18.05),
	(73,'2023-02-23 17:47:31',25,87,81.96),
	(74,'2023-02-15 16:35:56',3,64,9.96),
	(75,'2023-02-20 19:32:29',38,30,46.11),
	(76,'2023-02-11 14:02:24',41,37,14.39),
	(77,'2023-02-20 01:51:43',9,55,52.44),
	(78,'2023-03-04 21:35:43',48,30,89.68),
	(79,'2023-02-09 12:52:47',9,51,95.93),
	(80,'2023-02-21 11:49:16',38,88,95.66),
	(81,'2023-02-28 20:12:35',-7,42,1.55),
	(82,'2023-03-05 22:47:52',-1,58,86.38),
	(83,'2023-02-22 23:53:13',29,88,26.99),
	(84,'2023-02-27 07:32:36',12,75,43.74),
	(85,'2023-02-20 16:13:34',19,86,62.27),
	(86,'2023-02-28 20:50:04',10,97,54.09),
	(87,'2023-02-11 11:10:28',35,49,55.85),
	(88,'2023-02-17 10:41:06',33,92,28.90),
	(89,'2023-02-19 01:08:40',40,58,49.64),
	(90,'2023-02-17 16:35:54',31,73,21.54),
	(91,'2023-02-12 08:47:19',11,61,43.03),
	(92,'2023-02-25 12:36:29',11,3,6.58),
	(93,'2023-02-24 06:45:42',18,11,63.50),
	(94,'2023-02-22 05:33:19',26,22,24.19),
	(95,'2023-02-24 01:06:22',-3,71,19.69),
	(96,'2023-02-17 09:40:27',29,28,17.20),
	(97,'2023-02-28 16:19:22',11,11,43.04),
	(98,'2023-02-17 20:17:00',23,51,45.04),
	(99,'2023-02-23 02:11:36',44,21,29.38),
	(100,'2023-02-06 02:21:34',28,30,63.34),
	(101,'2023-03-01 09:40:10',-3,63,89.60),
	(102,'2023-02-07 08:07:01',7,0,21.44),
	(103,'2023-02-05 14:49:45',32,83,40.93),
	(104,'2023-03-04 05:32:20',29,1,61.49),
	(105,'2023-02-21 17:01:09',44,61,45.47),
	(106,'2023-03-03 04:13:17',1,0,75.02),
	(107,'2023-02-23 04:57:38',19,63,25.82),
	(108,'2023-02-12 17:16:56',-5,56,47.62),
	(109,'2023-02-25 14:08:20',42,14,47.01),
	(110,'2023-02-09 15:49:32',-3,68,32.26),
	(111,'2023-02-10 22:25:51',6,85,87.63),
	(112,'2023-02-04 13:41:28',32,85,31.37),
	(113,'2023-03-03 18:51:43',21,99,28.12),
	(114,'2023-02-03 15:24:14',11,86,57.08),
	(115,'2023-03-06 18:51:14',0,0,0.00),
	(116,'2023-03-06 18:54:29',0,0,0.00),
	(117,'2023-03-06 18:55:27',0,0,0.00),
	(118,'2023-03-06 18:57:23',0,0,0.00),
	(119,'2023-03-06 18:57:45',0,0,0.00),
	(120,'2023-03-06 19:05:23',0,0,0.00),
	(121,'2023-03-06 19:05:44',0,0,0.00),
	(122,'2023-03-06 19:06:10',0,0,0.00),
	(123,'2023-03-06 19:06:22',0,0,0.00),
	(124,'2023-03-06 19:07:55',0,0,0.00),
	(125,'2023-03-06 19:08:24',0,0,0.00),
	(126,'2023-03-06 19:09:37',0,0,0.00),
	(127,'2023-03-06 19:15:52',0,0,0.00),
	(128,'2023-03-06 19:15:55',0,0,0.00),
	(129,'2023-03-06 19:32:25',0,0,0.00),
	(130,'2023-03-06 19:33:20',0,0,0.00),
	(131,'2023-03-06 19:33:44',0,0,0.00),
	(132,'2023-03-06 19:35:56',0,1,0.00),
	(133,'2023-03-06 19:36:19',0,1,0.00),
	(134,'2023-03-06 19:36:34',0,1,0.00),
	(135,'2023-03-06 19:36:35',0,1,0.00),
	(136,'2023-03-06 19:36:35',0,1,0.00),
	(137,'2023-03-06 19:36:35',0,1,0.00),
	(138,'2023-03-06 19:36:36',0,1,0.00),
	(139,'2023-03-06 19:36:36',0,1,0.00),
	(140,'2023-03-06 19:37:17',0,1,0.00),
	(141,'2023-03-06 19:37:24',0,2,0.00),
	(142,'2023-03-06 19:37:41',0,2,0.00),
	(143,'2023-03-06 19:37:56',0,2,0.00),
	(144,'2023-03-06 19:38:08',12,2,0.00),
	(145,'2023-03-06 19:38:18',12,2,0.90),
	(146,'2023-03-06 19:40:58',12,2,0.90),
	(147,'2023-03-07 16:27:29',0,0,0.00),
	(148,'2023-03-07 18:21:17',0,0,0.00),
	(149,'2023-03-07 18:24:38',0,0,0.00),
	(150,'2023-03-07 18:25:20',0,0,0.00),
	(151,'2023-03-07 18:42:58',0,0,0.00),
	(152,'2023-03-07 18:44:59',0,0,0.00),
	(153,'2023-03-07 18:47:11',0,0,0.00),
	(154,'2023-03-07 18:48:29',0,0,0.00),
	(155,'2023-03-07 18:51:02',0,0,0.00),
	(156,'2023-03-07 18:52:46',0,0,0.00),
	(157,'2023-03-07 18:56:20',0,0,0.00),
	(158,'2023-03-07 19:00:40',0,0,0.00),
	(159,'2023-03-07 19:03:24',0,0,0.00),
	(160,'2023-03-07 19:23:36',0,0,0.00),
	(161,'2023-03-07 19:23:47',0,0,0.00),
	(162,'2023-03-07 20:02:59',0,0,0.00),
	(163,'2023-03-07 20:05:31',1,1,1.00);

/*!40000 ALTER TABLE `DHT11` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table MQ5
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MQ5`;

CREATE TABLE `MQ5` (
  `Seq` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `Value` decimal(4,2) NOT NULL,
  `Time` datetime DEFAULT NULL,
  PRIMARY KEY (`Seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



# Dump of table plant
# ------------------------------------------------------------

DROP TABLE IF EXISTS `plant`;

CREATE TABLE `plant` (
  `plant_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '식물 등록번호',
  `plant_name` varchar(50) DEFAULT NULL COMMENT '식물 이름',
  PRIMARY KEY (`plant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `plant` WRITE;
/*!40000 ALTER TABLE `plant` DISABLE KEYS */;

INSERT INTO `plant` (`plant_id`, `plant_name`)
VALUES
	(1,'Western Giant Hyssop'),
	(2,'Mexican Blueberry'),
	(3,'Eucalyptus'),
	(4,'Goldenaster'),
	(5,'European Bird Cherry'),
	(6,'Wilson\'s Milkvetch'),
	(7,'Shasta Buckwheat'),
	(8,'Alaska Oniongrass'),
	(9,'Broadleaf Rosette Grass'),
	(10,'Coulter\'s Stickpea'),
	(11,'Antilles Clubmoss'),
	(12,'Summer Coralroot'),
	(13,'Bluehead Gilia'),
	(14,'Filmy Angelica'),
	(15,'Broadleaf Glandweed'),
	(16,'Yellowbeak Owl\'s-clover'),
	(17,'Rocky Mountain Hemlockparsley'),
	(18,'South American Tobacco'),
	(19,'Western Meadow-rue'),
	(20,'Cutleaf Spleenwort'),
	(21,'Fendler\'s Penstemon'),
	(22,'Rice Barnyardgrass'),
	(23,'Cabellos De Angel'),
	(24,'Clusterspike False Indigo'),
	(25,'Shasta Clarkia'),
	(26,'Drummond\'s Sleepydaisy'),
	(27,'Onestem Fleabane'),
	(28,'Good King Henry'),
	(29,'Red Buckwheat'),
	(30,'Nevada Bird\'s-foot Trefoil'),
	(31,'Austin\'s Sphagnum'),
	(32,'Pawale'),
	(33,'Fishscale Lichen'),
	(34,'Mojave Sage'),
	(35,'Thickleaf Phlox'),
	(36,'Julella Lichen'),
	(37,'Bellorita'),
	(38,'Mayleaf Four O\'clock'),
	(39,'Russet Buffaloberry'),
	(40,'Spearleaf Stonecrop'),
	(41,'Arctic Bryum Moss'),
	(42,'Storm Sedge'),
	(43,'Winged Flax'),
	(44,'Jepson\'s Blue Wildrye'),
	(45,'Intermediate Cottongrass'),
	(46,'Wand Airplant'),
	(47,'Dubautia'),
	(48,'Pond Flatsedge'),
	(49,'Mauritia'),
	(50,'California Brodiaea'),
	(51,'Bruised Lichen'),
	(52,'Arizona Alumroot'),
	(53,'Spearleaf Rabbitbrush'),
	(54,'Candle Snuffer Moss'),
	(55,'Suksdorf\'s Silene'),
	(56,'Low Sphagnum'),
	(57,'Sensitive Partridge Pea'),
	(58,'Bigflower Pawpaw'),
	(59,'Milla'),
	(60,'Forest Clermontia'),
	(61,'Pyrenees Star Of Bethlehem'),
	(62,'Sticky Goldenweed'),
	(63,'Ethiopian Rattlebox'),
	(64,'Cutleaf Balsamroot'),
	(65,'Maritime Saltbush'),
	(66,'Snow Lichen'),
	(67,'Mojave Panicgrass'),
	(68,'Slender Fleabane'),
	(69,'Pleuridium Moss'),
	(70,'Oahu Mirrorplant'),
	(71,'Hoaryleaf Ceanothus'),
	(72,'Canadian Plum'),
	(73,'Marshpepper Knotweed'),
	(74,'Resurrection Plant'),
	(75,'Deformed Cup Lichen'),
	(76,'Dot Lichen'),
	(77,'Trelease\'s Beavertail Pricklypear'),
	(78,'Common Wild Sorghum'),
	(79,'Hairy Fimbry'),
	(80,'Pennyroyal'),
	(81,'Heartleaf Skullcap'),
	(82,'Skunk Cabbage'),
	(83,'Threadleaf Beaksedge'),
	(84,'Swartz\'s Jamaican Broom'),
	(85,'Frosty Wattle'),
	(86,'Western Tansymustard'),
	(87,'Field Groundcherry'),
	(88,'Northwestern Twayblade'),
	(89,'Manzanita'),
	(90,'Summer Coralroot'),
	(91,'Bigelow\'s Porotrichum Moss'),
	(92,'Creek Wattle'),
	(93,'Pseudocrossidium Moss'),
	(94,'Petite Flamboyant Bauhinia'),
	(95,'Texas Palafox'),
	(96,'Lyreleaf Jewelflower'),
	(97,'Ctenolepis'),
	(98,'Fiveneedle Pricklyleaf'),
	(99,'Sugar Pine'),
	(100,'Davidson\'s Buckwheat');

/*!40000 ALTER TABLE `plant` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tmp
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tmp`;

CREATE TABLE `tmp` (
  `data_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `data_timestamp` timestamp NULL DEFAULT NULL,
  `data_datetime` date DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  PRIMARY KEY (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `user_pw` text DEFAULT NULL,
  `user_birthdate` date DEFAULT NULL,
  `user_createtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



# Dump of table user_plant
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_plant`;

CREATE TABLE `user_plant` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
