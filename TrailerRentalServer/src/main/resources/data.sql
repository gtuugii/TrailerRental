INSERT INTO trailer.role(role_id, role) VALUES (1, 'ADMIN');
INSERT INTO trailer.role(role_id, role) VALUES (2, 'TENANT');

INSERT INTO trailer.user(user_id, email, first_name, last_name, password, phone_number, picture_path, reg_date, sex, status)
values(1, "bganbold@mum.edu", "Battuguldur", "Ganbold", "12345", "1111", "picture", "2018-11-02", "0", "1");

INSERT INTO trailer.user(user_id, email, first_name, last_name, password, phone_number, picture_path, reg_date, sex, status)
values(2, "maralaa@mum.edu", "Guamaral", ".", "12345", "1111", "picture", "2018-11-02", "1", "1");

INSERT INTO trailer.user(user_id, email, first_name, last_name, password, phone_number, picture_path, reg_date, sex, status)
values(3, "ganaa@mum.edu", "Gankhuyag", ".", "12345", "1111", "picture", "2018-11-02", "0", "1");


INSERT INTO trailer.user_role(role_id, user_id) VALUES (1, 1);
INSERT INTO trailer.user_role(role_id, user_id) VALUES (2, 2);
INSERT INTO trailer.user_role(role_id, user_id) VALUES (3, 3);

