--liquibase formatted sql

--changeset bs:2017-06-17-10-functions splitStatements:false

CREATE OR REPLACE FUNCTION set_modified()
  RETURNS TRIGGER AS $$
BEGIN
  NEW.modified = clock_timestamp();
  RETURN NEW;
END;
$$ language 'plpgsql';
