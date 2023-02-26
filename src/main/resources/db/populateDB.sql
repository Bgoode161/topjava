DELETE
FROM user_role;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2022-02-25 08:00:00', 'завтрак', 500, 100000),
       ('2022-02-25 12:00:00', 'перекус', 300, 100000),
       ('2022-02-25 18:00:00', 'ужин', 800, 100000),
       ('2022-02-26 08:00:00', 'завтрак', 600, 10000),
       ('2022-02-26 12:00:00', 'перекус', 200, 100001),
       ('2022-02-26 18:00:00', 'ужин', 1000, 100001),
       ('2022-02-27 08:00:00', 'завтрак', 400, 100002),
       ('2022-02-27 12:00:00', 'перекус', 150, 100002);
