TRUNCATE users CASCADE ;
TRUNCATE photos;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER  SEQUENCE photo_counter RESTART WITH 1;

INSERT INTO users (name, age, contact_number, primary_email, flag)
VALUES ('Василий Иванович Терещенко', 27, '+380951342643', 'adminuser12@ukr.net','superuser'),
  ('Д.Н. Вовняко', 54, '+380967832343', 'dodo1234@gmail.com', 'active');

INSERT INTO photos (name, file)
VALUES ('Федорченко Игорь', 'file00001.jpg'),
  ('Иванова Светлана', 'file00002.jpg');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO emails (to_field, from_field, subject, message_body, user_id)
VALUES ('adminuser12@ukr.net', 'admin@miner2.org', 'Welcome in system!!!', 'Hi! We glad to see you in our exchange system!. Please confirm your email.', 100000),
  ('adminuser12@ukr.net', 'admin@miner2.org', 'Exchange completed', 'Hi! Your exchange operation with id 037846784 is completed.', 100000),
  ('dodo1234@gmail.com', 'admin@miner2.org', 'Welcome in system!!!', 'Hi! We glad to see you in our exchange system!. Please confirm your email.', 100001);