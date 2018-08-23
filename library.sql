SET NAMES utf8;

SET FOREIGN_KEY_CHECKS = 0;

-- ---------------------------
--  Table structure for `user`

-- ---------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ---------------------------
--  Records of `user`

-- ---------------------------

BEGIN;

INSERT INTO library.user(login, password, first_name, surname) VALUES('admin', 'admin', 'Jan', 'Kowalski');
COMMIT;

-- ---------------------------
--  Table structure for `books`

-- ---------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `first_release` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ---------------------------
--  Records of `books`

-- ---------------------------

BEGIN;

INSERT INTO library.books
(title, author, first_release) 
VALUES('Gra o tron', 'Martin', '1996'), ('Potop', 'Sienkiewicz', '1886'), ('Krol', 'Twardoch', '2015');
COMMIT;

-- ---------------------------
--  Table structure for `rentals`

-- ---------------------------
DROP TABLE IF EXISTS `rentals`;
CREATE TABLE `rentals` (
  
  `book_id` int(11) NOT NULL,
  
  `user_login` varchar(100) NOT NULL,
  
  PRIMARY KEY (`book_id`,`user_login`),
  
  KEY `user_login` (`user_login`),
  
  CONSTRAINT `rentals_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  
  CONSTRAINT `rentals_ibfk_2` FOREIGN KEY (`user_login`) REFERENCES `user` (`login`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ---------------------------
--  Records of `rentals`

-- ---------------------------

BEGIN;

INSERT INTO library.rentals(book_id, user_login) VALUES(3, 'admin');
COMMIT;

-- ---------------------------
--  Table structure for `reservations`

-- ---------------------------
DROP TABLE IF EXISTS `reservations`;
CREATE TABLE `reservations` (
  
  `book_id` int(11) NOT NULL,
  
  `user_login` varchar(100) NOT NULL,
  
  PRIMARY KEY (`book_id`,`user_login`),
  
  KEY `user_login` (`user_login`),
  
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  
  CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`user_login`) REFERENCES `user` (`login`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ---------------------------
--  Records of `reservations`

-- ---------------------------

BEGIN;

INSERT INTO library.reservations(book_id, user_login) VALUES(4, 'admin');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

SET @@global.time_zone = '+00:00';
SET @@session.time_zone = '+00:00';