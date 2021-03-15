DROP TABLE IF EXISTS user;

CREATE TABLE user (
	username VARCHAR(250) NOT NULL,
	password VARCHAR(250) NOT NULL,
	role VARCHAR(4) NOT NULL,
	enabled TINYINT(4) DEFAULT NULL,
	PRIMARY KEY (username)
);


INSERT INTO user (username, password, role, enabled) VALUES
	('oreng@gmail.com', 'password','USER', 1),
	('reiman@gmail.com', 'password','USER', 1);