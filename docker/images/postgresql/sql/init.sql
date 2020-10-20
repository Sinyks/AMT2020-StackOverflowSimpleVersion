DROP SCHEMA if exists stackoverflowsimple CASCADE;
CREATE SCHEMA stackoverflowsimple;
SET search_path TO stackoverflowsimple;
CREATE TABLE users(
    pk_user uuid NOT NULL,
    username  character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    aboutMe TEXT,
    password character varying(255) NOT NULL,
    CONSTRAINT ct_pk_user PRIMARY KEY(pk_user),
    CONSTRAINT ct_uk_username UNIQUE(username),
    CONSTRAINT ct_uk_email UNIQUE(email)
);