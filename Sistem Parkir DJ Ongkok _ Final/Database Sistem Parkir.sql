/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.20 : Database - database_1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`database_1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `database_1`;

/*Table structure for table `slot_parkir` */

DROP TABLE IF EXISTS `slot_parkir`;

CREATE TABLE `slot_parkir` (
  `Slot` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Status_ID` int unsigned NOT NULL DEFAULT '1',
  `Status` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Available',
  PRIMARY KEY (`Slot`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;

/*Data for the table `slot_parkir` */

insert  into `slot_parkir`(`Slot`,`Status_ID`,`Status`) values 
('A1',0,'Not Available'),
('A2',0,'Not Available'),
('A3',0,'Not Available'),
('A4',1,'Available'),
('A5',1,'Available'),
('A6',1,'Available'),
('B1',1,'Available'),
('B2',1,'Available'),
('B3',1,'Available'),
('B4',1,'Available'),
('B5',1,'Available'),
('B6',1,'Available'),
('C1',1,'Available'),
('C2',1,'Available'),
('C3',0,'Not Available'),
('C4',1,'Available'),
('C5',1,'Available'),
('C6',1,'Available'),
('D1',1,'Available'),
('D2',1,'Available'),
('D3',1,'Available'),
('D4',1,'Available'),
('D5',1,'Available'),
('D6',1,'Available');

/*Table structure for table `transaction` */

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `Plate` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Slot` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Slip_No` int unsigned NOT NULL,
  `Park_Time` int unsigned NOT NULL DEFAULT '0',
  `Payment_Status` int unsigned NOT NULL DEFAULT '0',
  `Price` int unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`Slip_No`),
  KEY `Slot` (`Slot`),
  CONSTRAINT `Slot` FOREIGN KEY (`Slot`) REFERENCES `slot_parkir` (`Slot`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;

/*Data for the table `transaction` */

insert  into `transaction`(`Plate`,`Slot`,`Slip_No`,`Park_Time`,`Payment_Status`,`Price`) values 
('B1234ABC','A1',1,3,1,40000),
('B1234ABC','A3',2,3,1,40000),
('B4455JKL','B2',3,3,1,40000),
('B5678KK','C5',4,6,1,100000),
('B9878JKL','D3',5,3,1,40000),
('b5678ol','C2',6,0,0,0),
('b2323VC','A1',7,0,0,0),
('B4545ty','A2',8,0,0,0),
('c5454gh','C3',9,0,0,0),
('B8792RPJ','A3',10,0,0,0),
('B8989EV','C1',11,0,0,0),
('B0000AA','A4',12,4,1,60000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
