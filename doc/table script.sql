CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	first_name VARCHAR(200),
	last_name VARCHAR(200),
	username VARCHAR(200),
	password VARCHAR(200)
);

CREATE TABLE tasks (
	task_id SERIAL PRIMARY KEY,
	title VARCHAR(200),
	description VARCHAR(8000),
	completed boolean,
	user_id INT,
	CONSTRAINT fk_tasks_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);



INSERT INTO users (first_name, last_name, username, "password") VALUES ('Emil', 'Negron', 'enegron', 'password123');



SELECT * FROM users