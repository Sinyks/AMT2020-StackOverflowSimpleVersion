DROP SCHEMA IF EXISTS stackoverflowsimple CASCADE;
CREATE SCHEMA IF NOT EXISTS stackoverflowsimple;
SET search_path TO stackoverflowsimple, public;

--
-- Creat user and grant prevelege
--

--
-- Table users
--
CREATE TABLE users(
    pk_user uuid NOT NULL,
    name character varying(32) NOT NULL,
    pseudo  character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    aboutMe TEXT,
    password character varying(255) NOT NULL,
    CONSTRAINT ct_pk_user PRIMARY KEY(pk_user),
    CONSTRAINT ct_uk_pseudo UNIQUE(pseudo),
    CONSTRAINT ct_uk_email UNIQUE(email)
);

--
-- Table posts
--

--
-- Table comments
--