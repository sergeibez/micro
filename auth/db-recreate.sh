#!/usr/bin/env sh
psql -U postgres -w -c "DROP DATABASE micro_auth"

psql -U postgres -w -c "
 CREATE DATABASE micro_auth WITH
 OWNER = micro
 ENCODING = 'UTF8'
 LC_COLLATE = 'de_DE.UTF-8'
 LC_CTYPE = 'de_DE.UTF-8'
 TABLESPACE = pg_default
 CONNECTION LIMIT = -1
 TEMPLATE template0"