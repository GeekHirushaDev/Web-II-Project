CREATE DATABASE IF NOT EXISTS `web_ii_hibernate`
USE `web_ii_hibernate`;

CREATE TABLE IF NOT EXISTS `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `brand` (`id`, `name`) VALUES
	(1, 'Apple'),
	(2, 'Samsung'),
	(3, 'Sony'),
	(4, 'Dell'),
	(5, 'Nike'),
	(6, 'Bookiya');

CREATE TABLE IF NOT EXISTS `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `qty` int NOT NULL,
  `brand_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_brand_idx` (`brand_id`),
  CONSTRAINT `fk_product_brand` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `product` (`id`, `name`, `price`, `qty`, `brand_id`) VALUES
	(1, 'iPhone 15', 999.99, 25, 1),
	(2, 'MacBook Pro', 1999.99, 12, 1),
	(3, 'Galaxy S23', 849.5, 35, 2),
	(4, 'Galaxy Tab S7', 650, 22, 2),
	(5, 'PlayStation 5', 499, 10, 3),
	(6, 'Sony WH-1000XM5', 399.99, 18, 3),
	(7, 'XPS 13', 1200, 15, 4),
	(8, 'Inspiron 15', 800, 20, 4),
	(9, 'Air Zoom Pegasus', 130, 50, 5),
	(10, 'Nike Hoodie', 75, 40, 5);