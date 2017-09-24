psql -U postgres -w -c "DROP DATABASE micro_person"

psql -U postgres -w -c ^"^
 CREATE DATABASE micro_person WITH^
 OWNER = micro^
 ENCODING = 'UTF8'^
 LC_COLLATE = 'German_Germany.1252'^
 LC_CTYPE = 'German_Germany.1252'^
 TABLESPACE = pg_default^
 CONNECTION LIMIT = -1^
 TEMPLATE template0