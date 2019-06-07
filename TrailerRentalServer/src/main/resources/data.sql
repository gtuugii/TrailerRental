INSERT INTO trailer.role(role_id, role) VALUES (1, 'ADMIN');
INSERT INTO trailer.role(role_id, role) VALUES (2, 'TENANT');

INSERT INTO trailer.user(email, first_name, last_name, password, phone_number, picture_path, reg_date, sex, status) values("bganbold@mum.edu", "Battuguldur", "Ganbold", "12345", "1111", "picture", "2018-11-02", "0", "1");

INSERT INTO trailer.user_role(role_id, user_id) VALUES (1, 1);
INSERT INTO trailer.user_role(role_id, user_id) VALUES (2, 2);
