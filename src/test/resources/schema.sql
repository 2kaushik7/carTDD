DROP TABLE IF EXISTS CARS;

CREATE TABLE cars (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(250) NOT NULL,
	type VARCHAR(250) NOT NULL,
	transmissiontype VARCHAR(250),
	enginetype VARCHAR(250),
	airbags BOOLEAN
);