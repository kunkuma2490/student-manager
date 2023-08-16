-- ----------------------------------------------------------------------------
-- Schema student
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `student`;
CREATE SCHEMA IF NOT EXISTS `student`;
USE `student`;

-- -------------------------------------------------
-- Table structure for table `student`
-- -------------------------------------------------

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`first_name` VARCHAR(255) NOT NULL,
	`last_name` VARCHAR(255) NOT NULL,
	`dob` DATE NOT NULL,
	`section` CHAR(1) NOT NULL,
	`gender` CHAR(1) NOT NULL,
	`marks1` INT DEFAULT NULL,
	`marks2` INT DEFAULT NULL,
	`marks3` INT DEFAULT NULL,
	`total` INT DEFAULT NULL,
	`average` INT DEFAULT NULL,
	`result` VARCHAR(100) DEFAULT NULL,
	`created_by` VARCHAR(50) NOT NULL DEFAULT 'SYSTEM',
	`created_datetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`modified_by` VARCHAR(50) NOT NULL DEFAULT 'SYSTEM',
	`modified_datetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;