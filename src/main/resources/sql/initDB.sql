DROP TABLE IF EXISTS users CASCADE;
DROP TYPE IF EXISTS user_flag;

DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS emails;
DROP TABLE IF EXISTS photos;

DROP SEQUENCE IF EXISTS global_seq;
DROP SEQUENCE IF EXISTS photo_counter;

CREATE TYPE user_flag AS ENUM ('active', 'deleted', 'superuser');

CREATE SEQUENCE global_seq START 100000;

CREATE SEQUENCE photo_counter START 1;

CREATE TABLE users (
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name              VARCHAR(100) NOT NULL,
  age               INTEGER NOT NULL,
  contact_number    VARCHAR(30) NOT NULL,
  primary_email     VARCHAR(30) NOT NULL,
  flag              VARCHAR(20) NOT NULL
);

CREATE TABLE photos (
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name              VARCHAR(100) NOT NULL,
  file              VARCHAR(100) NOT NULL,
  is_human          BOOLEAN NOT NULL DEFAULT FALSE,
  counter           INTEGER DEFAULT nextval('photo_counter')
);

CREATE UNIQUE INDEX email_idx ON users (primary_email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE emails (
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  to_field          VARCHAR(100) NOT NULL ,
  from_field        VARCHAR(100) NOT NULL ,
  subject           VARCHAR(200),
  message_body      VARCHAR(4000),
  user_id           INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

