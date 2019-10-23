SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE users;
TRUNCATE TABLE user_roles;
TRUNCATE TABLE meals;

INSERT INTO users (name, email, password) VALUES
('User', 'user@yandex.ru', 'password'),
('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
('ROLE_USER', 100000),
('ROLE_ADMIN', 100001);

INSERT INTO meals (id, dateTime, description, calories, user_id) VALUES
#Meal for admin
(1, '2015-07-01 14:00:00', 'Админ ланч', 510, 100001),
(2, '2015-07-01 21:00:00', 'Админ ужин', 1500, 100001),

#Meal for admin
(3, '2015-05-30 10:00:00', 'Завтрак', 500, 100000),
(4, '2015-05-30 13:00:00', 'Обед', 1000, 100000),
(5, '2015-05-30 20:00:00', 'Ужин', 500, 100000),
(6, '2015-05-31 10:00:00', 'Завтрак', 1000, 100000),
(7, '2015-05-31 13:00:00', 'Обед', 500, 100000),
(8, '2015-05-31 20:00:00', 'Ужин', 510, 100000);

SET FOREIGN_KEY_CHECKS = 1;