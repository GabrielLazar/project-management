--ROLES
INSERT INTO roles(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, description)
VALUES ('2021-01-01 00:00:00', 1, '2021-01-01 00:00:00', 1, 'Admin');

INSERT INTO roles(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, description)
VALUES ('2021-01-01 00:00:00', 1, '2021-01-01 00:00:00', 1, 'Manager');

INSERT INTO roles(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id, description)
VALUES ('2021-01-01 00:00:00', 1, '2021-01-01 00:00:00', 1, 'Employee');
--USERS
INSERT INTO users(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,first_name,last_name,user_name,email,birth_date,phone_number,gender,enabled,role_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 00:00:00',1,
'admin','admin','admin','admin@projectmanagement.com','2000-01-01','1234567890','MALE',true,1);

