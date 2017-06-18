--liquibase formatted sql

--changeset bs:2017-06-16-01-databasechangelog

ALTER TABLE databasechangelog ADD CONSTRAINT databasechangelog_pk PRIMARY KEY (id);
