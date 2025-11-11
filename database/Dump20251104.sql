CREATE DATABASE  IF NOT EXISTS `login` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `login`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: login
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumnos` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nombres` varchar(120) NOT NULL,
  `apellidos` varchar(120) NOT NULL,
  `promedio` decimal(4,2) NOT NULL DEFAULT '0.00',
  `carrera` varchar(16) NOT NULL,
  `creado_en` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `actualizado_en` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_alumnos_carrera` (`carrera`),
  CONSTRAINT `fk_alumnos_programa` FOREIGN KEY (`carrera`) REFERENCES `programa` (`codigo`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `ck_alumnos_promedio` CHECK ((`promedio` between 0.00 and 5.00))
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos`
--

LOCK TABLES `alumnos` WRITE;
/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
INSERT INTO `alumnos` VALUES (1,'Juana','Pérez Ríos',4.50,'SIS','2025-10-23 11:15:48','2025-10-23 11:15:48'),(2,'Marco','López Díaz',3.20,'ADM','2025-10-23 11:15:48','2025-10-23 11:15:48'),(3,'Luis','Rojas',0.00,'AGR','2025-10-25 12:48:46','2025-10-25 12:48:46'),(4,'Maria','Pinilla',0.00,'SOC','2025-10-25 12:49:19','2025-10-25 12:49:19'),(5,'Jairo','Martinez Diaz',2.40,'ELE','2025-10-25 12:50:17','2025-10-25 12:50:17'),(6,'Estiven','Rodriguez',2.90,'ZOO','2025-10-25 12:50:57','2025-10-25 12:50:57'),(7,'Victor','Roque',3.30,'CON','2025-10-25 12:51:27','2025-10-25 12:51:27'),(8,'Jhonatan','Acuña Romero',3.30,'IND','2025-10-25 12:51:56','2025-10-25 12:51:56'),(9,'Gabriel','Hernandez',2.20,'MEC','2025-10-25 12:52:23','2025-10-25 12:52:23'),(10,'Marcela','Diaz',3.20,'ADM','2025-10-29 12:14:22','2025-10-29 12:14:22'),(11,'Esteban','Hernandez',4.00,'ADM','2025-10-29 12:16:10','2025-10-29 12:16:10');
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programa`
--

DROP TABLE IF EXISTS `programa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programa` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `codigo` varchar(16) NOT NULL,
  `nombre` varchar(120) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programa`
--

LOCK TABLES `programa` WRITE;
/*!40000 ALTER TABLE `programa` DISABLE KEYS */;
INSERT INTO `programa` VALUES (1,'SIS','Ingeniería de Sistemas'),(2,'ADM','Administración de Empresas'),(3,'IND','Ingeniería Industrial'),(4,'ELE','Ingeniería Electrónica'),(5,'AGR','Agronomía'),(7,'CON','Contaduría Pública'),(8,'SOC','Licenciatura en Sociales'),(9,'ZOO','Zootecnia'),(10,'MEC','Ingeniería Mecatrónica');
/*!40000 ALTER TABLE `programa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `codigo` varchar(32) NOT NULL,
  `nombre` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ADMIN','Administrador'),(2,'DOCENTE','Docente');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `ingresoUsuario` varchar(120) NOT NULL,
  `ingresoContrasenia` varchar(255) NOT NULL,
  `tipo_de_usuario` varchar(32) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `ultimo_acceso` datetime DEFAULT NULL,
  `creado_en` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `actualizado_en` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ingresoUsuario` (`ingresoUsuario`),
  KEY `idx_usuarios_tipo_de_usuario` (`tipo_de_usuario`),
  CONSTRAINT `fk_usuarios_rol` FOREIGN KEY (`tipo_de_usuario`) REFERENCES `rol` (`codigo`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','$2y$10$E9nPo.Eys2SbkDXOiv1sEuLT4Va2eG5n0UZANUeAFX3K8t1Bz88.i','ADMIN',1,'2025-10-29 13:49:32','2025-10-23 11:15:48','2025-10-29 13:49:32'),(2,'matematicasseptimosemestre','$2y$10$V.zxPP46wb4LPaRyZKb56u9YUUfwDjKVXv.gRIlj934010X2D8XO6','DOCENTE',1,NULL,'2025-10-29 12:11:31','2025-10-29 12:11:31');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-04 23:08:59
