INSERT INTO roles(insert_date_time, insert_user_id, last_update_date_time, last_update_user_id, description)
VALUES ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, 'Admin'),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, 'Manager'),
       ('2022-01-05 00:00:00', 1, '2022-01-05 00:00:00', 1, 'Employee');

INSERT INTO users(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                  first_name, last_name, user_name, gender, phone, role_id)
VALUES ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'admin', 'admin', 'admin@admin.com', 'MALE', '0000000000', 1),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Harold', 'Finch', 'harold@manager.com', 'MALE', '0123456789', 2),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Samantha', 'Groves', 'samantha@manager.com', 'MALE', '9876543210', 2),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'John', 'Reese', 'john@employee.com', 'MALE', '7894561230', 3),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Sameen', 'Shaw', 'sameen@employee.com', 'MALE', '0321654987', 3),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Grace', 'Hendricks', 'grace@employee.com', 'MALE', '7410258963', 3),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Lionel', 'Fusco', 'lionel@employee.com', 'MALE', '3698520147', 3);

INSERT INTO projects(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, project_code, project_name,
                     project_detail, project_status, start_date, end_date, manager_id)
VALUES ('2022-01-05 00:00:00', 2, false, '2022-01-05 00:00:00', 2, 'SP00', 'Spring Core', 'Spring Core Project', 'OPEN', '2022-01-05', '2022-06-12', 2),
       ('2022-01-05 00:00:00', 2, false, '2022-01-05 00:00:00', 2, 'SP01', 'Spring Boot', 'Spring Boot Project', 'IN_PROGRESS', '2022-01-05', '2022-06-12', 2),
       ('2022-01-05 00:00:00', 3, false, '2022-01-05 00:00:00', 3, 'SP02', 'Spring MVC', 'Spring MVC Project', 'IN_PROGRESS', '2022-01-05', '2022-06-12', 3),
       ('2022-01-05 00:00:00', 3, false, '2022-01-05 00:00:00', 3, 'SP03', 'Spring Data', 'Spring Data Project', 'OPEN', '2022-01-05', '2022-06-12', 3);