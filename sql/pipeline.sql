CREATE DATABASE pipeline;
USE pipeline;

CREATE TABLE pipeline (
	id INT(10) NOT NULL AUTO_INCREMENT,
	name VARCHAR(256),
	description VARCHAR(256),
	PRIMARY KEY (id)
);

CREATE TABLE task (
	id INT(10) NOT NULL AUTO_INCREMENT,
	name VARCHAR(256),
	description VARCHAR(256),
	pipeline_id INT(10) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (pipeline_id) REFERENCES pipeline(id)
);

CREATE TABLE action (
	id INT(10) NOT NULL AUTO_INCREMENT,
	type VARCHAR(256),
	task_id INT(10) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (task_id) REFERENCES task(id)
);

/* transitions */

CREATE TABLE pipeline_execution_status (
	id INT(10) NOT NULL AUTO_INCREMENT,
	pipeline VARCHAR(256),
	status VARCHAR(256),
	start_time VARCHAR(256),
	PRIMARY KEY (id)
);

CREATE TABLE task_execution_status (
	id INT(10) NOT NULL AUTO_INCREMENT,
	name VARCHAR(256),
	status VARCHAR(256),/*изменить на DATE*/
	start_time VARCHAR(256),
	end_time VARCHAR(256),
	pipeline_execution_status_id INT(10) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (pipeline_execution_status_id) REFERENCES pipeline_execution_status(id)
);