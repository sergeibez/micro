--liquibase formatted sql

--changeset bs:2017-09-01-40-user

-- users
CREATE TABLE users (
  id       d_id,
  version  d_version,
  created  d_created,
  modified d_modified,

  username citext NOT NULL DEFAULT '',
  note     d_note,

  CONSTRAINT users_pk PRIMARY KEY (id),
  CONSTRAINT users_username_uk UNIQUE (username)
);
CREATE TRIGGER users_trg_bu_modified BEFORE UPDATE ON users FOR EACH ROW EXECUTE PROCEDURE set_modified();

INSERT INTO users (id, username) VALUES
  (0, ''),
  (1, 'Developer'),
  (2, 'Support'),
  (3, 'Staff'),
  (4, 'Admin'),
  (5, 'Member'),
  (6, 'Guest');
