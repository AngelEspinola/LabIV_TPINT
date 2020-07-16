CREATE DATABASE  IF NOT EXISTS `bdtpint` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bdtpint`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: bdtpint
-- ------------------------------------------------------
-- Server version	5.7.29-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `alumnonotas`
--

DROP TABLE IF EXISTS `alumnonotas`;
/*!50001 DROP VIEW IF EXISTS `alumnonotas`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `alumnonotas` AS SELECT 
 1 AS `ID`,
 1 AS `legajo`,
 1 AS `nombre`,
 1 AS `apellido`,
 1 AS `id_curso`,
 1 AS `nota1`,
 1 AS `nota2`,
 1 AS `recuperatorio1`,
 1 AS `recuperatorio2`,
 1 AS `estado`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumnos` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `legajo` varchar(45) NOT NULL,
  `dni` varchar(10) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `nacimiento` date NOT NULL,
  `calle` varchar(45) NOT NULL,
  `numero` varchar(5) NOT NULL,
  `id_localidad` int(11) NOT NULL,
  `id_provincia` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `estado` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_DNI` (`dni`),
  UNIQUE KEY `UK_Legajo` (`legajo`),
  KEY `id_provincia` (`id_provincia`),
  KEY `id_localidad` (`id_localidad`),
  CONSTRAINT `alumnos_ibfk_1` FOREIGN KEY (`id_provincia`) REFERENCES `provincias` (`ID`),
  CONSTRAINT `alumnos_ibfk_2` FOREIGN KEY (`id_localidad`) REFERENCES `localidades` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos`
--

LOCK TABLES `alumnos` WRITE;
/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursos` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `docente` int(11) NOT NULL,
  `cuatrimestre` int(11) NOT NULL,
  `año` int(11) NOT NULL,
  `materia` int(11) NOT NULL,
  `aprobados` int(11) NOT NULL DEFAULT '0',
  `porcentajeAprobados` decimal(18,2) NOT NULL DEFAULT '0.00',
  `desaprobados` int(11) NOT NULL DEFAULT '0',
  `porcentajeDesaprobados` decimal(18,2) NOT NULL DEFAULT '0.00',
  `promedio1` decimal(18,2) NULL DEFAULT '0.00',
  `promedio2` decimal(18,2) NOT NULL DEFAULT '0.00',
  `baja` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `cursos_FK_idx` (`docente`),
  KEY `materia_FK` (`materia`),
  CONSTRAINT `cursos_FK` FOREIGN KEY (`docente`) REFERENCES `docentes` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `materia_FK` FOREIGN KEY (`materia`) REFERENCES `materias` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES (4,1,2,2020,7,0);
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alumnoxcurso`
--

DROP TABLE IF EXISTS `alumnoxcurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumnoxcurso` (
  `id_curso` int(11) NOT NULL,
  `id_alumno` int(11) NOT NULL,
  `nota1` int(11) DEFAULT NULL,
  `nota2` int(11) DEFAULT NULL,
  `recuperatorio1` int(11) DEFAULT NULL,
  `recuperatorio2` int(11) DEFAULT NULL,
  `PromedioTotal` decimal(18,2) AS
((ifnull(`nota1`,0)+ifnull(`nota2`,0)+ifnull(`recuperatorio1`,0)+ifnull(`recuperatorio2`,0))/
(if(ifnull(`nota1`,0)>0,1,0)+if(ifnull(`nota2`,0)>0,1,0)+if(ifnull(`recuperatorio1`,0)>0,1,0)+if(ifnull(`recuperatorio2`,0)>0,1,0))) STORED NULL,
  `PromedioParciales` decimal(18,2) AS
(((ifnull(`nota1`,0))+ifnull(`nota2`,0))/(if(ifnull(`nota1`,0)>0,1,0)+if(ifnull(`nota2`,0)>0,1,0)))STORED NULL,
  `Promedio` decimal(18,2) AS
((if(ifnull(`recuperatorio1`,0)>0,`recuperatorio1`,ifnull(`nota1`,0))+if(ifnull(`recuperatorio2`,0)>0,`recuperatorio2`,ifnull(`nota2`,0)))/
(if(if(ifnull(`recuperatorio1`,0)>0,`recuperatorio1`,ifnull(`nota1`,0))>0,1,0)+if(if(ifnull(`recuperatorio2`,0)>0,`recuperatorio2`,ifnull(`nota2`,0))>0,1,0)))STORED NULL,
  `aprobado` int(11) AS (IF((ifnull(`Promedio`,0) >= 6),_utf8mb4'1',_utf8mb4'0'))STORED,
  `estado` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id_curso`,`id_alumno`),
  KEY `FK_Alumnos` (`id_alumno`),
  CONSTRAINT `alumnoxcurso_ibfk_1` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`ID`),
  CONSTRAINT `alumnoxcurso_ibfk_2` FOREIGN KEY (`id_alumno`) REFERENCES `alumnos` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DELIMITER ;;
DROP TRIGGER IF EXISTS `calcularPromedio`;
CREATE TRIGGER `calcularPromedio` AFTER UPDATE ON `alumnoXcurso`
FOR EACH ROW
	update `cursos` set
    `promedio1` = (SELECT AVG(`promedio`) FROM `alumnoXcurso` WHERE `alumnoXcurso`.`id_curso` = `ID`),
    `promedio2` = (SELECT AVG(`PromedioTotal`) FROM `alumnoXcurso` WHERE `alumnoXcurso`.`id_curso` = `ID`),
    `aprobados` = (SELECT COUNT(DISTINCT `alumnoXcurso`.`id_alumno`) FROM `alumnoXcurso` WHERE `alumnoXcurso`.`id_curso` = `ID` AND `alumnoXcurso`.`aprobado`= 1),
    `desaprobados` = (SELECT COUNT(DISTINCT `alumnoXcurso`.`id_alumno`) FROM `alumnoXcurso` WHERE `alumnoXcurso`.`id_curso` = `ID` AND `alumnoXcurso`.`aprobado`= 0),
    `porcentajeAprobados` = ifnull(ifnull(`aprobados`,0)/(ifnull(`aprobados`,0)+ifnull(`desaprobados`,0)),0)*100,
    `porcentajeDesaprobados` = ifnull(ifnull(`desaprobados`,0)/(ifnull(`aprobados`,0)+ifnull(`desaprobados`,0)),0)*100
    where `ID` = new.`id_curso`;
DELIMITER ;

--
-- Dumping data for table `alumnoxcurso`
--

LOCK TABLES `alumnoxcurso` WRITE;
/*!40000 ALTER TABLE `alumnoxcurso` DISABLE KEYS */;
/*!40000 ALTER TABLE `alumnoxcurso` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `docentes`
--

DROP TABLE IF EXISTS `docentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `docentes` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `legajo` varchar(45) NOT NULL,
  `dni` varchar(10) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `nacimiento` date NOT NULL,
  `calle` varchar(45) NOT NULL,
  `numero` varchar(5) NOT NULL,
  `id_localidad` int(11) NOT NULL,
  `id_provincia` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `estado` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_DNI` (`dni`),
  UNIQUE KEY `UK_Legajo` (`legajo`),
  KEY `id_localidad` (`id_localidad`),
  KEY `id_provincia` (`id_provincia`),
  CONSTRAINT `docentes_ibfk_1` FOREIGN KEY (`id_localidad`) REFERENCES `localidades` (`ID`),
  CONSTRAINT `docentes_ibfk_2` FOREIGN KEY (`id_provincia`) REFERENCES `provincias` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docentes`
--

LOCK TABLES `docentes` WRITE;
/*!40000 ALTER TABLE `docentes` DISABLE KEYS */;
INSERT INTO `docentes` VALUES (1,'21706','33123456','Juan','Suarez','1980-12-31','Calle','500',1,4,'mes@gmail.com','1511223344',_binary '');
/*!40000 ALTER TABLE `docentes` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `crearusuarios` AFTER INSERT ON `docentes`
FOR EACH ROW
INSERT INTO `usuarios`(`id_docente`,`usuario`,`clave`,`estado`,`id_rol`)
VALUES(new.ID,new.legajo,new.dni,1,2) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `localidades`
--

DROP TABLE IF EXISTS `localidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `localidades` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `id_provincia` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_Localidades` (`nombre`),
  KEY `FK_Localidades` (`id_provincia`),
  CONSTRAINT `localidades_ibfk_1` FOREIGN KEY (`id_provincia`) REFERENCES `provincias` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidades`
--

LOCK TABLES `localidades` WRITE;
/*!40000 ALTER TABLE `localidades` DISABLE KEYS */;
INSERT INTO `localidades` VALUES (1,1,'Bancalari'),(2,1,'Don Torcuato'),(3,1,'El Talar'),(4,1,'La Plata'),(5,1,'Pacheco'),(6,1,'Villa de Mayo'),(7,1,'Virreyes'),(8,2,'San Fernando del Valle de Catamarca'),(9,3,'Resistencia'),(10,4,'Rawson'),(11,5,'CÃ³rdoba'),(12,6,'Corrientes'),(13,7,'ParanÃ¡'),(14,8,'Formosa'),(15,9,'San Salvador de Jujuy'),(16,10,'Santa Rosa'),(17,11,'La Rioja'),(18,12,'Mendoza'),(19,13,'Misiones'),(20,14,'NeuquÃ©n'),(21,15,'RÃ­o Negro'),(22,16,'Salta'),(23,17,'San Juan'),(24,18,'San Luis'),(25,19,'RÃ­o Gallegos'),(26,20,'Santa Fe'),(27,21,'Santiago del Estero'),(28,22,'Ushuaia'),(29,23,'San Miguel de TucumÃ¡n'),(30,24,'Saavedra');
/*!40000 ALTER TABLE `localidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materias`
--

DROP TABLE IF EXISTS `materias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materias` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_Materias` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materias`
--

LOCK TABLES `materias` WRITE;
/*!40000 ALTER TABLE `materias` DISABLE KEYS */;
INSERT INTO `materias` VALUES (4,'Arquitectura y Sistemas Operativos'),(5,'Estadistica'),(6,'Ingles I'),(7,'Ingles II'),(1,'Introduccion a la Informatica'),(2,'Introduccion a la Programacion'),(8,'Laboratorio de Computacion I'),(9,'Laboratorio de Computacion II'),(10,'Matematica'),(3,'Matematica (TSP)'),(11,'Metodologia de la Investigacion'),(12,'Programacion I'),(13,'Programacion II');
/*!40000 ALTER TABLE `materias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provincias` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_Provincias` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincias`
--

LOCK TABLES `provincias` WRITE;
/*!40000 ALTER TABLE `provincias` DISABLE KEYS */;
INSERT INTO `provincias` VALUES (1,'Buenos Aires'),(24,'CABA'),(2,'Catamarca'),(5,'CÃ³rdoba'),(3,'Chaco'),(4,'Chubut'),(6,'Corrientes'),(7,'Entre RÃ­os'),(8,'Formosa'),(9,'Jujuy'),(10,'La Pampa'),(11,'La Rioja'),(12,'Mendoza'),(13,'Misiones'),(14,'NeuquÃ©n'),(15,'RÃ­o Negro'),(16,'Salta'),(17,'San Juan'),(18,'San Luis'),(19,'Santa Cruz'),(20,'Santa Fe'),(21,'Santiago del Estero'),(22,'Tierra del Fuego'),(23,'TucumÃ¡n');
/*!40000 ALTER TABLE `provincias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_Roles` (`descripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Administrador'),(2,'Docente');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `id_docente` int(11) DEFAULT NULL,
  `usuario` varchar(20) NOT NULL,
  `clave` varchar(20) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `id_rol` int(11) DEFAULT NULL,
  PRIMARY KEY (`usuario`),
  UNIQUE KEY `UK_Usuarios` (`ID`),
  KEY `FK_Usuario` (`id_docente`),
  KEY `FK_Rol` (`id_rol`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_docente`) REFERENCES `docentes` (`ID`),
  CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,1,'21706','33123456',_binary '',2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bdtpint'
--

--
-- Dumping routines for database 'bdtpint'
--

--
-- Final view structure for view `alumnonotas`
--

/*!50001 DROP VIEW IF EXISTS `alumnonotas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `alumnonotas` AS select `alumnos`.`ID` AS `ID`,`alumnos`.`legajo` AS `legajo`,`alumnos`.`nombre` AS `nombre`,`alumnos`.`apellido` AS `apellido`,`axc`.`id_curso` AS `id_curso`,`axc`.`nota1` AS `nota1`,`axc`.`nota2` AS `nota2`,`axc`.`recuperatorio1` AS `recuperatorio1`,`axc`.`recuperatorio2` AS `recuperatorio2`,`alumnos`.`estado` AS `estado` from (`alumnoxcurso` `axc` join `alumnos` on((`alumnos`.`ID` = `axc`.`id_alumno`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-11  1:50:42
