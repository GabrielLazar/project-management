--ROLES
INSERT INTO roles(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, description)
VALUES ('2021-01-01 00:00:00', 1, '2021-01-01 01:00:00', 1, 'Admin');

INSERT INTO roles(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, description)
VALUES ('2021-01-01 00:00:00', 1, '2021-01-01 02:00:00', 1, 'Manager');

INSERT INTO roles(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id, description)
VALUES ('2021-01-01 00:00:00', 1, '2021-01-01 03:00:00', 1, 'Employee');
--USERS
INSERT INTO users(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,first_name,last_name,user_name,email,birth_date,phone_number,gender,enabled,role_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 01:00:00',1,
'admin','admin','admin','admin@projectmanagement.com','2000-01-01','1234567890','MALE',true,1);
INSERT INTO users(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,first_name,last_name,user_name,email,birth_date,phone_number,gender,enabled,role_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 02:00:00',1,
'Paola','Bonelli','pbonelli','p.bonelli@projectmanagement.com','1980-03-27','3126789201','FEMALE',true,2);
INSERT INTO users(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,first_name,last_name,user_name,email,birth_date,phone_number,gender,enabled,role_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 03:00:00',1,
'Selma','Gruber','sgruber','s.gruber@projectmanagement.com','1983-11-02','2134423121','FEMALE',true,2);
INSERT INTO users(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,first_name,last_name,user_name,email,birth_date,phone_number,gender,enabled,role_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 04:00:00',1,
'Vlad','Popesco','vpopesco','v.popesco@projectmanagement.com','1988-01-23','5213110230','MALE',true,1);
INSERT INTO users(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,first_name,last_name,user_name,email,birth_date,phone_number,gender,enabled,role_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 05:00:00',1,
'Adrian','Noel','anoel','a.noel@projectmanagement.com','1990-05-20','7445698213','MALE',true,1);
INSERT INTO users(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,first_name,last_name,user_name,email,birth_date,phone_number,gender,enabled,role_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 06:00:00',1,
'Diego','Pizarro','dpizarro','d.pizarro@projectmanagement.com','2000-01-01','8975541233','MALE',true,2);
INSERT INTO users(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,first_name,last_name,user_name,email,birth_date,phone_number,gender,enabled,role_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 07:00:00',1,
'Said','Aly','saly','s.aly@projectmanagement.com','1977-05-04','2415678892','MALE',true,3);
INSERT INTO users(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,first_name,last_name,user_name,email,birth_date,phone_number,gender,enabled,role_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 08:00:00',1,
'Madalina','Maglione','mmaglione','m.maglione@projectmanagement.com','1983-08-12','2341577890','FEMALE',true,3);
INSERT INTO users(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,first_name,last_name,user_name,email,birth_date,phone_number,gender,enabled,role_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 09:00:00',1,
'Sebastian','Brown','sbrown','s.brown@projectmanagement.com','1978-09-25','2567801234','MALE',true,3);
INSERT INTO users(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,first_name,last_name,user_name,email,birth_date,phone_number,gender,enabled,role_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 10:00:00',1,
'Claudia','Schultz','cschultz','c.schultz@projectmanagement.com','1983-12-29','4545678912','FEMALE',true,3);
--PROJECTS
INSERT INTO projects(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,project_name,project_code,start_date,project_detail,project_status,user_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 01:00:00',1,
'Random Password Generator','RPG','2021-01-01','A random password generator is a software program that automatically generates a password using parameters that a user sets.','OPEN',2);
INSERT INTO projects(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,project_name,project_code,start_date,project_detail,project_status,user_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 02:00:00',1,
'Nutrition/Fitness Tracker','NFT','2021-01-01','A fitness tracker can help an individual in self-monitoring their activities. It helps in setting fitness goals such as meeting a basic regular exercise activity goal, burning a specific number of calories in the day.','OPEN',3);
INSERT INTO projects(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,project_name,project_code,start_date,project_detail,project_status,user_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 03:00:00',1,
'Mad Libs Creator','MLC','2021-01-01','Mad Libs is a phrasal template word game which consists of one player prompting others for a list of words to substitute for blanks in a story before reading aloud.','OPEN',2);
INSERT INTO projects(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,project_name,project_code,start_date,project_detail,project_status,user_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 04:00:00',1,
'Image Caption Generator','ICG','2021-01-01','Image caption generator is a task that involves computer vision and natural language processing concepts to recognize the context of an image and describe them in a natural language like English.','OPEN',3);
INSERT INTO projects(insert_date_time, insert_user_id,  last_update_date_time, last_update_user_id,project_name,project_code,start_date,project_detail,project_status,user_id)
VALUES ('2021-01-01 00:00:00',1,'2021-01-01 05:00:00',1,
'Sports Scores Tracker','SST','2021-01-01','Application that tracks the sport results.','OPEN',2);