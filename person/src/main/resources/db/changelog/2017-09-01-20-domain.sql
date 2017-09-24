--liquibase formatted sql

--changeset bs:2017-09-01-20-domains

-- id
CREATE SEQUENCE sequence_id START 1000000 INCREMENT 1;
CREATE DOMAIN d_id AS bigint NOT NULL DEFAULT nextval('sequence_id'::regclass);

-- header
CREATE DOMAIN d_version AS integer NOT NULL DEFAULT 0;
CREATE DOMAIN d_created AS timestamp NOT NULL DEFAULT clock_timestamp();
CREATE DOMAIN d_modified AS timestamp NOT NULL DEFAULT clock_timestamp();

-- common
CREATE DOMAIN d_string AS text NOT NULL DEFAULT '';
CREATE DOMAIN d_name AS d_string;
CREATE DOMAIN d_note AS d_string;
CREATE DOMAIN d_text AS d_string;
CREATE DOMAIN d_locale AS d_string;
