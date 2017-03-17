FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=root

ADD ./db/1-SCHEMA.sql /docker-entrypoint-initdb.d/1-SCHEMA.sql
ADD ./db/2-PRIVILEGES.sql /docker-entrypoint-initdb.d/2-PRIVILEGES.sql
ADD ./db/3-ENTRIES.sql /docker-entrypoint-initdb.d/3-ENTRIES.sql

