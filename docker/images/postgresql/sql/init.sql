DROP SCHEMA if exists stackoverflowsimple CASCADE;
CREATE SCHEMA stackoverflowsimple;
SET search_path TO stackoverflowsimple;
CREATE TABLE users(
    pk_user uuid NOT NULL,
    username  character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    aboutMe TEXT,
    password character varying(255) NOT NULL,
    CONSTRAINT usersct_pk_user PRIMARY KEY(pk_user),
    CONSTRAINT usersct_uk_username UNIQUE(username),
    CONSTRAINT usersct_uk_email UNIQUE(email)
);

CREATE TABLE questions(
    pk_question uuid NOT NULL,
    fk_ownerId uuid NOT NULL,
    title  character varying(255) NOT NULL,
    creationDate timestamp NOT NULL,
    lastEditDate timestamp NOT NULL,
    body TEXT NOT NULL,
    CONSTRAINT questionsct_pk_question PRIMARY KEY(pk_question),
    CONSTRAINT questionsct_pk_fk_ownerId FOREIGN KEY(fk_ownerId) REFERENCES users(pk_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT questionsct_uk_title UNIQUE(title)
);

CREATE TABLE answers(
    pk_answer uuid NOT NULL,
    fk_ownerId uuid NOT NULL,
    fk_questionId uuid NOT NULL,
    creationDate timestamp NOT NULL,
    lastEditDate timestamp NOT NULL,
    body TEXT NOT NULL,
    CONSTRAINT answersct_pk_question PRIMARY KEY(pk_answer),
    CONSTRAINT answersct_pk_fk_ownerId FOREIGN KEY(fk_ownerId) REFERENCES users(pk_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT answersct_pk_fk_questionId FOREIGN KEY(fk_questionId) REFERENCES questions(pk_question)  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE comments(
    pk_comment uuid NOT NULL,
    fk_ownerId uuid NOT NULL,
    fk_questionId uuid NULL,
    fk_answerId uuid NULL,
    creationDate timestamp NOT NULL,
    lastEditDate timestamp NOT NULL,
    body TEXT NOT NULL,
    CONSTRAINT comments_pk_question PRIMARY KEY(pk_comment),
    CONSTRAINT comments_pk_fk_ownerId FOREIGN KEY(fk_ownerId) REFERENCES users(pk_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT comments_pk_fk_questionId FOREIGN KEY(fk_questionId) REFERENCES questions(pk_question)  ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT comments_pk_fk_answerId FOREIGN KEY(fk_answerId) REFERENCES answers(pk_answer)  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE votes(
    pk_vote uuid NOT NULL,
    fk_ownerId uuid NOT NULL,
    fk_questionId uuid NULL,
    fk_answerId uuid NULL,
    isUpVote boolean NOT NULL,
    CONSTRAINT votes_pk_vote PRIMARY KEY(pk_vote),
    CONSTRAINT votes_pk_fk_ownerId FOREIGN KEY(fk_ownerId) REFERENCES users(pk_user) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT votes_pk_fk_questionId FOREIGN KEY(fk_questionId) REFERENCES questions(pk_question)  ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT votes_pk_fk_answerId FOREIGN KEY(fk_answerId) REFERENCES answers(pk_answer)  ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT votes_uk_fk_ownerId_and_fk_questionId UNIQUE (fk_ownerId,fk_questionId),
    CONSTRAINT votes_uk_kf_ownerId_and_fk_answerId UNIQUE (fk_ownerId,fk_answerId)
);

CREATE TABLE tags(
    pk_tag uuid NOT NULL,
    name character varying(255) NOT NULL,
    CONSTRAINT tags_pk_tag PRIMARY KEY(pk_tag),
    CONSTRAINT tags_uk_tag UNIQUE (name)
);


