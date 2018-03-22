-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.22-rc-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema leavemgmt
--

CREATE DATABASE IF NOT EXISTS leavemgmt;
USE leavemgmt;

--
-- Definition of table `leaveapp`
--

DROP TABLE IF EXISTS `leaveapp`;
CREATE TABLE `leaveapp` (
  `Date_Leave` date NOT NULL,
  `Reason` varchar(100) DEFAULT NULL,
  `Status` int(11) NOT NULL,
  `UserId` varchar(12) NOT NULL,
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leaveapp`
--

/*!40000 ALTER TABLE `leaveapp` DISABLE KEYS */;
INSERT INTO `leaveapp` (`Date_Leave`,`Reason`,`Status`,`UserId`,`Id`) VALUES 
 ('2017-08-12','Testing',2,'akshay',1),
 ('2017-08-15','Independence Day',1,'akshay',2),
 ('2018-03-09','kjk',2,'akshay',3);
/*!40000 ALTER TABLE `leaveapp` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `EmpId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserId` varchar(12) NOT NULL,
  `Password` varchar(12) NOT NULL,
  `UserName` varchar(45) NOT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `Country` varchar(45) NOT NULL,
  `State` varchar(45) NOT NULL,
  `ZipCode` int(10) unsigned NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Sex` varchar(1) NOT NULL,
  `Language` varchar(1) NOT NULL,
  `About` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`UserId`) USING BTREE,
  UNIQUE KEY `Index_2` (`EmpId`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`EmpId`,`UserId`,`Password`,`UserName`,`Address`,`Country`,`State`,`ZipCode`,`Email`,`Sex`,`Language`,`About`) VALUES 
 (100,'admin','password','Administrator','','India','Rajasthan',30208,'admin@mgmt.com','M','E',''),
 (101,'akshay','123456789','Akshay Patni','Jaipur','India','Rajasthan',302003,'m.akshaypatni@gmail.com','M','N','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
