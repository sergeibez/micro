--liquibase formatted sql

--changeset bs:2017-09-01-30-functions splitStatements:false

CREATE OR REPLACE FUNCTION set_modified()
  RETURNS TRIGGER AS $$
BEGIN
  NEW.modified = clock_timestamp();
  RETURN NEW;
END;
$$ language 'plpgsql';
