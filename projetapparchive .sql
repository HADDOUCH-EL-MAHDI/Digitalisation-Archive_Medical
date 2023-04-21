-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le : sam. 04 juin 2022 à 15:27
-- Version du serveur :  8.0.21
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projetapparchive`
--

-- --------------------------------------------------------

--
-- Structure de la table `patients`
--

DROP TABLE IF EXISTS `patients`;
CREATE TABLE IF NOT EXISTS `patients` (
  `ip` int NOT NULL,
  `code` varchar(20) NOT NULL,
  `id` int NOT NULL,
  `nomETprenom` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `dateE` date NOT NULL,
  `dateS` date NOT NULL,
  `diagnosticE` varchar(255) NOT NULL,
  `diagnosticS` varchar(255) NOT NULL,
  `pr` varchar(255) NOT NULL,
  `dr` varchar(255) NOT NULL,
  PRIMARY KEY (`ip`),
  KEY `id` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `patients`
--

INSERT INTO `patients` (`ip`, `code`, `id`, `nomETprenom`, `tel`, `adresse`, `dateE`, `dateS`, `diagnosticE`, `diagnosticS`, `pr`, `dr`) VALUES
(80, 'H123', 0, 'Mahdi', '43535', '4242', '2012-06-08', '2013-06-09', 'DFGGFGD', 'GFDGFDG', 'FDSFDSFD', 'FSDFDSF'),
(7000, 'G12', 4, 'fjlsfjkgjs', '54544', 'FD', '2019-06-12', '2020-06-03', 'xxxxxxxxxxx', 'xxxxxxxx', 'vvvvvvv', 'bbbbbbbb'),
(34, 'G34', 4, 'JJJJJJJJJ', '3453', 'mmmm', '2017-06-01', '2022-06-02', 'ffgfd', 'gdfgdg', 'ggfdgdf', 'gdfgfdg'),
(46465, 'J23', 4, 'AZE', '4356', '535345', '2016-06-03', '2017-06-02', 'fdgdg', 'fggss', 'gdfgd', 'gfdg'),
(555, 'H555', 4, 'gdddhhdh', '35536565', 'gfhhf', '2000-04-01', '2020-04-01', 'gfggfg', 'gfgfg', 'gfgffg', 'gfgfgg'),
(8000, 'f330', 4, 'dfg', '56775', 'dsf', '2022-03-07', '2022-06-26', 'dsfdsfdsf', 'fsdsfdsf', 'fsdffd', 'dfsdfd');

-- --------------------------------------------------------

--
-- Structure de la table `services`
--

DROP TABLE IF EXISTS `services`;
CREATE TABLE IF NOT EXISTS `services` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `AdminPassword` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `services`
--

INSERT INTO `services` (`id`, `nom`, `mot_de_passe`, `AdminPassword`) VALUES
(1, 'ophtalmologie', 'ophtalmologie2014', 'ophtalmologieAdmin'),
(2, 'neurologie', 'neurologie2014', 'neurologieAdmin'),
(3, 'cardiologie', 'cardiologie2014', 'cardiologieAdmin'),
(4, 'urgences', 'urgences2014', 'urgencesAdmin'),
(5, 'dermatologie', 'dermatologie2014', 'dermatologieAdmin'),
(6, 'radiothérapie', 'radiothérapie2014', 'radiothérapieAdmin');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
