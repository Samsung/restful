CREATE DATABASE `data` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `note` (
  `idx` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `idx_UNIQUE` (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
