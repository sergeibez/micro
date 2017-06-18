--liquibase formatted sql

--changeset bs:2017-06-16-10-extensions

CREATE EXTENSION IF NOT EXISTS citext;
CREATE EXTENSION IF NOT EXISTS btree_gist;
