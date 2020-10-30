FROM postgres:latest
ENV  POSTGRES_USER=postgres
ENV  POSTGRES_PASSWORD=postgres
COPY sql/1-init.sql /docker-entrypoint-initdb.d/
COPY sql/2-data.sql /docker-entrypoint-initdb.d/
