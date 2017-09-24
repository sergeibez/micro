--liquibase formatted sql

--changeset bs:2017-09-01-01-databasechangelog

ALTER TABLE databasechangelog ADD CONSTRAINT databasechangelog_pk PRIMARY KEY (id);
