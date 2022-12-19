CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	username VARCHAR(200) UNIQUE,
	password VARCHAR(200),
	user_type VARCHAR(200)
);

CREATE TABLE tasks (
	ticket_id SERIAL PRIMARY KEY,
	ticket_type VARCHAR(200),
	description VARCHAR(8000),
	status VARCHAR(200),
	user_id INT,
	CONSTRAINT fk_tasks_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);



INSERT INTO users (first_name, last_name, username, "password", user_type) VALUES ('Emil', 'Negron', 'enegron', 'password123', 'manager');



SELECT * FROM users