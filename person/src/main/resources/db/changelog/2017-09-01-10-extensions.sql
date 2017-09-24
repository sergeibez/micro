--liquibase formatted sql

--changeset bs:2017-09-01-10-extensions

CREATE EXTENSION IF NOT EXISTS citext;
CREATE EXTENSION IF NOT EXISTS btree_gist;
