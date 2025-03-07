-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: bd_spring_gastos
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estados` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES (1,'Activo'),(2,'No activo'),(3,'Pagado'),(4,'No pagado');
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gastos_fijos`
--

DROP TABLE IF EXISTS `gastos_fijos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gastos_fijos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) DEFAULT NULL,
  `monto` bigint DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `estados_id` bigint DEFAULT NULL,
  `proveedores_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr2aa4n2gwvohguxdd88immbbn` (`estados_id`),
  KEY `FK5b1wft9noglkw0cufheq3qbfy` (`proveedores_id`),
  CONSTRAINT `FK5b1wft9noglkw0cufheq3qbfy` FOREIGN KEY (`proveedores_id`) REFERENCES `proveedores` (`id`),
  CONSTRAINT `FKr2aa4n2gwvohguxdd88immbbn` FOREIGN KEY (`estados_id`) REFERENCES `estados` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gastos_fijos`
--

LOCK TABLES `gastos_fijos` WRITE;
/*!40000 ALTER TABLE `gastos_fijos` DISABLE KEYS */;
INSERT INTO `gastos_fijos` VALUES (1,'2025-01-20 00:00:00.000000',1222222,'Pago energía eléctrica',3,1),(2,'2025-03-20 00:00:00.000000',34242,'Agua potable',4,3),(3,'2025-03-20 00:00:00.000000',1024521,'Cuota del automóvil',3,5),(4,'2025-02-20 00:00:00.000000',34566,'Cuenta del gas',4,6),(5,'2025-02-20 00:00:00.000000',76575,'Cuenta del transportista',4,1),(6,'2025-03-20 00:00:00.000000',6666,'Pago colegiatura',3,4),(7,'2025-03-20 00:00:00.000000',5454,'Cuenta de internet',4,3),(8,'2025-03-22 00:00:00.000000',9896,'Cuota préstamo',4,6),(11,'2025-03-02 22:07:31.524000',100000,'Prueba Angular',4,7),(12,'2025-03-02 22:10:02.904000',150000,'Crédito Banco Inventado',3,1);
/*!40000 ALTER TABLE `gastos_fijos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gastos_por_dia`
--

DROP TABLE IF EXISTS `gastos_por_dia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gastos_por_dia` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` text,
  `fecha` datetime(6) DEFAULT NULL,
  `iva` float DEFAULT NULL,
  `neto` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `proveedores_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaxn9xv2e1j68a3bm7okt88mwr` (`proveedores_id`),
  CONSTRAINT `FKaxn9xv2e1j68a3bm7okt88mwr` FOREIGN KEY (`proveedores_id`) REFERENCES `proveedores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gastos_por_dia`
--

LOCK TABLES `gastos_por_dia` WRITE;
/*!40000 ALTER TABLE `gastos_por_dia` DISABLE KEYS */;
INSERT INTO `gastos_por_dia` VALUES (1,'Compra televisor','2025-02-10 00:00:00.000000',19,100,119,1),(2,'Mouse óptico','2025-02-06 16:36:38.000000',19,100,119,5),(3,'Mesa de computador','2025-03-10 00:00:00.000000',19,100,119,1),(5,'Notebook gammer ','2025-03-10 17:48:24.794000',16,600,696,2),(6,'Compra almuerzo','2025-03-14 10:27:53.756000',19,1234,1468.46,1),(11,'Teclado RGB','2025-03-03 12:11:30.833000',15,250,287.5,2),(12,'Crédito por mes Modificado','2025-03-03 12:43:10.081000',12,150000,168000,7);
/*!40000 ALTER TABLE `gastos_por_dia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
INSERT INTO `perfil` VALUES (1,'Administrador'),(2,'Usuario');
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedores` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES (1,'Proveedor 1'),(2,'Proveedor 2'),(3,'Proveedor 3 '),(4,'Proveedor 4'),(5,'Proveedor desde postman'),(6,'Proveedor con getter and setters'),(7,'Proveedor angular 17 modificado'),(8,'Proveedor 8 Modificado'),(9,'Proveedor Desde Cliente Actualizado'),(10,'Proveedor Angular');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `correo` varchar(255) DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `perfil_id` bigint DEFAULT NULL,
  `estados_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7m0djhoy0rtstg2ochneq020v` (`perfil_id`),
  KEY `FKfs05bbigkqbpxybtsqvgp55o3` (`estados_id`),
  CONSTRAINT `FK7m0djhoy0rtstg2ochneq020v` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`),
  CONSTRAINT `FKfs05bbigkqbpxybtsqvgp55o3` FOREIGN KEY (`estados_id`) REFERENCES `estados` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (2,'juanito1111@prueba.com','2025-02-22 20:34:17.406000','Juan Pérez López','$2a$10$0.Wd22Y29B/JA3Ph1q.kAuOMA0e.F2BdEEGvAPeW5qKYF3Vz5nbtW','',2,1),(3,'juanito1111@prueba.com.mx','2025-02-24 18:09:01.257000','Juan Pérez López','$2a$10$EJUz/hnauqhrPCPpQF8Hs.CnNknkP67/mbneL5b8LfvIi2c6U4Meu','',2,1),(4,'octavio@prueba.com','2025-02-28 00:11:40.186000','Octavio Silva','$2a$10$EJUz/hnauqhrPCPpQF8Hs.CnNknkP67/mbneL5b8LfvIi2c6U4Meu','',1,1),(5,'Prueba@gmail.com','2025-03-02 19:41:01.186000','Fulano','$2a$10$pJw6S.CnL2QCaTSFBxaQiO9sGHnAk2wRVH.SFrWQYYFn0Y43BT6Py','',2,1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `variables_globales`
--

DROP TABLE IF EXISTS `variables_globales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `variables_globales` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variables_globales`
--

LOCK TABLES `variables_globales` WRITE;
/*!40000 ALTER TABLE `variables_globales` DISABLE KEYS */;
INSERT INTO `variables_globales` VALUES (1,'Base URL','http://localhost:8009/'),(2,'secreto jwt','9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d99a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8p2x9');
/*!40000 ALTER TABLE `variables_globales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'bd_spring_gastos'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-07 13:13:06
