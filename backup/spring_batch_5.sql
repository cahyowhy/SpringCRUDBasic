-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.28-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for spring_batch_5
CREATE DATABASE IF NOT EXISTS `spring_batch_5` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `spring_batch_5`;

-- Dumping structure for table spring_batch_5.dosen
CREATE TABLE IF NOT EXISTS `dosen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table spring_batch_5.dosen: ~2 rows (approximately)
DELETE FROM `dosen`;
/*!40000 ALTER TABLE `dosen` DISABLE KEYS */;
INSERT INTO `dosen` (`id`, `name`, `created_at`, `birth_date`) VALUES
	(1, 'amir hamzah', '2017-12-25', '1970-12-25'),
	(2, 'amar hanif', '2017-12-25', '1970-12-25'),
	(3, 'hanif riski', '2017-12-25', '1970-12-25');
/*!40000 ALTER TABLE `dosen` ENABLE KEYS */;

-- Dumping structure for table spring_batch_5.kelas_matakuliah
CREATE TABLE IF NOT EXISTS `kelas_matakuliah` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `matakuliah_id` int(11) NOT NULL,
  `dosen_id` int(11) DEFAULT NULL,
  `room` varchar(50) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `matakuliah_id` (`matakuliah_id`),
  KEY `dosen_id` (`dosen_id`),
  CONSTRAINT `FK_kelas_matakuliah_dosen` FOREIGN KEY (`dosen_id`) REFERENCES `dosen` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_kelas_matakuliah_matakuliah` FOREIGN KEY (`matakuliah_id`) REFERENCES `matakuliah` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Dumping data for table spring_batch_5.kelas_matakuliah: ~6 rows (approximately)
DELETE FROM `kelas_matakuliah`;
/*!40000 ALTER TABLE `kelas_matakuliah` DISABLE KEYS */;
INSERT INTO `kelas_matakuliah` (`id`, `name`, `matakuliah_id`, `dosen_id`, `room`, `created_at`) VALUES
	(1, 'TI3', 4, 1, '11', '2016-12-26'),
	(2, 'TI1', 2, 2, '22', '2017-12-26'),
	(3, 'TI1', 3, 2, '23', '2017-12-26'),
	(4, 'TI1', 4, 2, '24', '2017-12-26'),
	(5, 'TI2', 1, 1, '25', '2017-12-26'),
	(6, 'TI2', 2, 1, '26', '2017-12-26'),
	(7, 'TI2', 3, 1, '27', '2017-12-26'),
	(8, 'TI3', 1, 3, '21', '2017-12-26'),
	(9, 'TI3', 2, 3, '21', '2017-12-26'),
	(10, 'TI2', 4, 1, '11', '2017-12-26');
/*!40000 ALTER TABLE `kelas_matakuliah` ENABLE KEYS */;

-- Dumping structure for table spring_batch_5.mahasiswa
CREATE TABLE IF NOT EXISTS `mahasiswa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- Dumping data for table spring_batch_5.mahasiswa: ~14 rows (approximately)
DELETE FROM `mahasiswa`;
/*!40000 ALTER TABLE `mahasiswa` DISABLE KEYS */;
INSERT INTO `mahasiswa` (`id`, `name`, `created_at`, `birth_date`) VALUES
	(1, 'cahyo wibowo', '2017-12-25', '1996-12-25'),
	(2, 'satrio kumolo', '2017-12-25', '1996-12-25'),
	(3, 'narendro', '2017-12-25', '1996-12-25'),
	(4, 'asih waluyo', '2017-12-25', '1996-12-25'),
	(5, 'rama prabawa', '2017-12-25', '1996-12-25'),
	(6, 'mukti hafidz', '2017-12-25', '1996-12-25'),
	(7, 'samsara', '2017-12-26', '1996-04-18'),
	(8, 'samsara', '2017-12-26', '1996-04-18'),
	(9, 'samsara', '2017-12-26', '1996-04-18'),
	(11, 'samsara', '2017-12-26', '1996-04-18'),
	(13, 'sarah', '2017-12-26', '1996-04-18'),
	(15, 'samsara', '2017-12-26', '1996-04-18'),
	(16, 'samsara', '2017-12-26', '1996-04-18'),
	(17, 'samsara', '2017-12-26', '1996-04-18');
/*!40000 ALTER TABLE `mahasiswa` ENABLE KEYS */;

-- Dumping structure for table spring_batch_5.mahasiswa_jadwal
CREATE TABLE IF NOT EXISTS `mahasiswa_jadwal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mahasiswa_id` int(11) DEFAULT NULL,
  `kelas_matakuliah_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mahasiswa_id` (`mahasiswa_id`),
  KEY `kelas_matakuliah_id` (`kelas_matakuliah_id`),
  CONSTRAINT `FK_mahasiswa_jadwal_kelas_matakuliah` FOREIGN KEY (`kelas_matakuliah_id`) REFERENCES `kelas_matakuliah` (`id`),
  CONSTRAINT `FK_mahasiswa_jadwal_mahasiswa` FOREIGN KEY (`mahasiswa_id`) REFERENCES `mahasiswa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table spring_batch_5.mahasiswa_jadwal: ~4 rows (approximately)
DELETE FROM `mahasiswa_jadwal`;
/*!40000 ALTER TABLE `mahasiswa_jadwal` DISABLE KEYS */;
INSERT INTO `mahasiswa_jadwal` (`id`, `mahasiswa_id`, `kelas_matakuliah_id`) VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 1, 3),
	(4, 1, 4);
/*!40000 ALTER TABLE `mahasiswa_jadwal` ENABLE KEYS */;

-- Dumping structure for table spring_batch_5.mahasiswa_matkul
CREATE TABLE IF NOT EXISTS `mahasiswa_matkul` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matakuliah_id` int(11) DEFAULT NULL,
  `mahasiswa_id` int(11) DEFAULT NULL,
  `nilai` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `matakuliah_id` (`matakuliah_id`),
  KEY `mahasiswa_id` (`mahasiswa_id`),
  CONSTRAINT `FK__matakuliah` FOREIGN KEY (`matakuliah_id`) REFERENCES `matakuliah` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_mahasiswa_matkul_mahasiswa` FOREIGN KEY (`mahasiswa_id`) REFERENCES `mahasiswa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table spring_batch_5.mahasiswa_matkul: ~0 rows (approximately)
DELETE FROM `mahasiswa_matkul`;
/*!40000 ALTER TABLE `mahasiswa_matkul` DISABLE KEYS */;
/*!40000 ALTER TABLE `mahasiswa_matkul` ENABLE KEYS */;

-- Dumping structure for table spring_batch_5.matakuliah
CREATE TABLE IF NOT EXISTS `matakuliah` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `sks_count` varchar(3) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table spring_batch_5.matakuliah: ~4 rows (approximately)
DELETE FROM `matakuliah`;
/*!40000 ALTER TABLE `matakuliah` DISABLE KEYS */;
INSERT INTO `matakuliah` (`id`, `name`, `sks_count`, `created_at`) VALUES
	(1, 'matematika', '2', '2017-12-26'),
	(2, 'bahasa indonesia', '3', '2017-12-26'),
	(3, 'bahasa inggris', '3', '2017-12-26'),
	(4, 'ipa', '2', '2017-12-26');
/*!40000 ALTER TABLE `matakuliah` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
