--liquibase formatted sql

--changeset bs:2017-06-17-10-user

-- user_group
CREATE TABLE user_group (
  id       d_id,
  version  d_version,
  created  d_created,
  modified d_modified,

  name     d_name,
  note     d_note,

  CONSTRAINT user_group_pk PRIMARY KEY (id),
  CONSTRAINT user_group_name_uk UNIQUE (name)
);
CREATE TRIGGER user_group_trg_bu_modified BEFORE UPDATE ON user_group FOR EACH ROW EXECUTE PROCEDURE set_modified();

-- users
CREATE TABLE users (
  id       d_id,
  version  d_version,
  created  d_created,
  modified d_modified,

  group_id                     bigint NOT NULL DEFAULT 0,

  username                     citext NOT NULL DEFAULT '',
  password                     text NOT NULL,

  email                        text NOT NULL DEFAULT '',
  locale                       d_locale,

  enabled                      boolean NOT NULL DEFAULT 'TRUE',

  account_locked_until         timestamp,
  account_expire_date_time     timestamp,
  credentials_expire_date_time timestamp,

  note                         d_note,

  CONSTRAINT users_pk PRIMARY KEY (id),
  CONSTRAINT users_username_uk UNIQUE (username),
  CONSTRAINT users_email_ex EXCLUDE USING GIST (lower(email) WITH =) WHERE (email != ''),
  CONSTRAINT users_group_fk FOREIGN KEY (group_id) REFERENCES user_group (id) ON DELETE CASCADE
);
CREATE TRIGGER users_trg_bu_modified BEFORE UPDATE ON users FOR EACH ROW EXECUTE PROCEDURE set_modified();

-- user_role
CREATE TABLE user_role (
  id       d_id,
  version  d_version,
  created  d_created,
  modified d_modified,

  parent_id bigint,

  name      d_name,
  note      d_note,

  CONSTRAINT user_role_pk PRIMARY KEY (id),
  CONSTRAINT user_role_name_uk UNIQUE(name),
  CONSTRAINT user_role_fk FOREIGN KEY (parent_id) REFERENCES user_role (id) ON DELETE CASCADE
);
CREATE TRIGGER user_role_trg_bu_modified BEFORE UPDATE ON user_role FOR EACH ROW EXECUTE PROCEDURE set_modified();

-- user_group_role
CREATE TABLE user_group_role (
  id       d_id,
  version  d_version,
  created  d_created,
  modified d_modified,

  group_id bigint NOT NULL,
  role_id  bigint NOT NULL,

  note     d_note,

  CONSTRAINT user_group_role_pk PRIMARY KEY (id),
  CONSTRAINT user_group_role_group_fk FOREIGN KEY (group_id) REFERENCES user_group (id) ON DELETE CASCADE,
  CONSTRAINT user_group_role_role_fk FOREIGN KEY (role_id) REFERENCES user_role (id) ON DELETE CASCADE,
  CONSTRAINT user_group_role_uk UNIQUE (group_id, role_id)
);
CREATE TRIGGER user_group_role_trg_bu_modified BEFORE UPDATE ON user_group_role FOR EACH ROW EXECUTE PROCEDURE set_modified();

-- Data
INSERT INTO user_role (id, parent_id, name) VALUES
  (0, null, 'IS_AUTHENTICATED_ANONYMOUSLY'),

  (1, 0,    'ROLE_DEVELOPER'),
  (2, 0,    'ROLE_SUPPORT'),

  (3, 0,    'ROLE_STAFF'),
  (4, 3,    'ROLE_ADMIN'),

  (5, 0,    'ROLE_CUSTOMER'),
  (6, 5,    'ROLE_MEMBER'),
  (7, 5,    'ROLE_GUEST');

INSERT INTO user_group (id, name) VALUES
  (0, ''),
  (1, 'Developer'),
  (2, 'Support'),
  (3, 'Staff'),
  (4, 'Admin'),
  (5, 'Member'),
  (6, 'Guest');

INSERT INTO users (id, group_id, username, password, note, email, locale, enabled) VALUES
  (0, 0, '',          '$2a$10$CDTNQ9OX5F1x98CrehorueURlbDV0N9z9vJOyxoOjgCCwc.uwppGK', 'System user', '', 'en_US', FALSE),
  (1, 1, 'Developer', '$2a$10$uUissBOglUMCAwMU3fgwd..cSxQWIXsPBthTD31uIJn4jGYdTSsGK', '', 'developer@email.com', 'en_US', TRUE),
  (2, 2, 'Support',   '$2a$10$e.Pm0q7bip/mlrZHGqbrcOSu2pWi1W0V9yLJR37wcmM5Lrk3kW2ou', '', 'support@email.com', 'en_US', TRUE),
  (3, 3, 'Staff',     '$2a$10$gFrM9Y8Qm5wV7vXPJnNoW.vI2/LQKxxsW3rnfLd1jvCNOjZIMHIqe', '', 'staff@email.com', 'en_US', TRUE),
  (4, 4, 'Admin',     '$2a$10$y6Nqa/C9LYz28ajxKT9L6.VnkYACbsUDtZKFkFgm6tdKe5Y5FeIl6', '', 'admin@email.com', 'en_US', TRUE),
  (5, 5, 'Member',    '$2a$10$2nSb/j/0FCj36posFXfV1OAdampEzInI7.R.gTgG6A4/XxqE3xdxO', '', 'member@email.com', 'en_US', TRUE),
  (6, 6, 'Guest',     '$2a$10$rp/QYU/WbS8SoXz0RjUsD.wI.Sw5GdWc.iwmSKMsdZqHCgm9eI.xa', '', 'guest@email.com', 'en_US', TRUE);

INSERT INTO user_group_role (id, group_id, role_id, note) VALUES
  (1, 1, 1, 'Developer'),
  (2, 2, 2, 'Support'),
  (3, 3, 3, 'Staff'),
  (4, 4, 4, 'Admin'),
  (5, 5, 5, 'Member'),
  (6, 6, 6, 'Guest');
