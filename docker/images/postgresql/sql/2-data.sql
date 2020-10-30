SET search_path TO stackoverflowsimple;

INSERT INTO "users" ("pk_user", "username", "email", "aboutme", "password") VALUES
('191605e5-f95e-499e-abc4-c34935bc7aad',	'guillaume',	'guillaume@email.com',	'no informations',	'$2a$10$lrOpOZPVz5dr.TnP5i2/NOJhkFJm2uY9.mBy3f/VJJGqcBOrTesaC'),
('7923a554-e15d-4ec0-a07f-436b9784d220',	'alban',	'alban@email.com',	'no informations',	'$2a$10$gN5gRwO0TjCQC22gjC2o/ekbgxuxElu4v3kjeZyCDmkKR0qIn1Zie'),
('ce954774-f8ba-47d9-bd61-dcc6af173e2d',	'test',	'test@email.com',	'no informations',	'$2a$10$JnLPtjWOTOcjur2x529uqOx7Cl80dZVnP/zLITzIchKox.rtFsRPu'),
('f44b34c6-978c-47d1-ae97-bbee20e200ff',	'Jojo',	'jojo@email.com',	'I am Jojo',	'$2a$10$HYrQKrmlNYpq5d/K5.ogcuM9JlZaem6dCnOj3Iu96Q3pkzJ4JwZtK');

INSERT INTO "questions" ("pk_question", "fk_ownerid", "title", "creationdate", "lasteditdate", "body") VALUES
('46597b48-38f7-467b-a1c3-58cdd00b58db',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	'What is love?',	'2020-10-29 00:00:00',	'2020-10-29 00:00:00',	'Baby don''t hurt me, don''t hurt me, no more.'),
('d54015e7-50b3-449c-a616-b61e3fb48725',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	'This is another question',	'2020-10-29 00:00:00',	'2020-10-29 00:00:00',	'How many questions do we need?'),
('ac25bea0-cdca-4607-8ef7-2021afc62c09',	'191605e5-f95e-499e-abc4-c34935bc7aad',	'User Guillaume is here',	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'Hi Everyone, I''m going to be posting questions here.'),
('05d48428-ac57-4ed4-b242-4109be9ca2b6',	'ce954774-f8ba-47d9-bd61-dcc6af173e2d',	'Test',	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'ceci est une question'),
('5d90e07b-faed-479c-afe7-2a79122949d5',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	'Question for increasing question count',	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'Is this edgy enough?');

INSERT INTO "answers" ("pk_answer", "fk_ownerid", "fk_questionid", "creationdate", "lasteditdate", "body") VALUES
('a0a27a54-732b-49d0-8a96-01dd6df71fd1',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	'46597b48-38f7-467b-a1c3-58cdd00b58db',	'2020-10-29 00:00:00',	'2020-10-29 00:00:00',	'Neuroscience says that....'),
('1f0e3b8a-7ca7-44fb-8b95-dc5da95be412',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	'46597b48-38f7-467b-a1c3-58cdd00b58db',	'2020-10-29 00:00:00',	'2020-10-29 00:00:00',	'Love is everyyyyywhere!'),
('99f73c4c-5183-4721-94dc-73c255f4f243',	'191605e5-f95e-499e-abc4-c34935bc7aad',	'ac25bea0-cdca-4607-8ef7-2021afc62c09',	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'This is where you should post your answers to my question.'),
('b2c99aca-1cda-4cfa-83b8-c03cedd56a3d',	'ce954774-f8ba-47d9-bd61-dcc6af173e2d',	'46597b48-38f7-467b-a1c3-58cdd00b58db',	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'ceci est ume  reponse'),
('e86c95be-97be-4f12-bc94-bb14d7429cb7',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	'05d48428-ac57-4ed4-b242-4109be9ca2b6',	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'I want to test if I can correctly manage votes'),
('54e5b9f2-c4d2-44a5-9bf4-3c5e30cddaae',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	'05d48428-ac57-4ed4-b242-4109be9ca2b6',	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'This is another answer to check if there''s other errors linked to the view and/or voting'),
('a33e9c90-7190-405d-bd28-e5ead0b9f763',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	'5d90e07b-faed-479c-afe7-2a79122949d5',	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'Just to increase answerCount');

INSERT INTO "comments" ("pk_comment", "fk_ownerid", "fk_questionid", "fk_answerid", "creationdate", "lasteditdate", "body") VALUES
('fb78b158-0508-4bba-a9f0-876753b07b4c',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	'46597b48-38f7-467b-a1c3-58cdd00b58db',	NULL,	'2020-10-29 00:00:00',	'2020-10-29 00:00:00',	'I wanna know what love is : I want you to show me.'),
('32a93a86-bca7-435e-b1f8-521715de6d15',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	'46597b48-38f7-467b-a1c3-58cdd00b58db',	NULL,	'2020-10-29 00:00:00',	'2020-10-29 00:00:00',	'Never gonna give you up, never gonna let you down, never gonna turn around and desert you.'),
('110e8d75-52d6-4550-97c4-9bca00868f04',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	NULL,	'a0a27a54-732b-49d0-8a96-01dd6df71fd1',	'2020-10-29 00:00:00',	'2020-10-29 00:00:00',	'Great answer!'),
('5a03b161-9e70-4655-a44b-ec9e1d131452',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	NULL,	'a0a27a54-732b-49d0-8a96-01dd6df71fd1',	'2020-10-29 00:00:00',	'2020-10-29 00:00:00',	'Looking for cute singles in your area?'),
('eb067f82-d3e0-4376-b779-25db8fbec787',	'191605e5-f95e-499e-abc4-c34935bc7aad',	'ac25bea0-cdca-4607-8ef7-2021afc62c09',	NULL,	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'Nobody has commented yet :('),
('682054da-b0fa-4133-ba91-4d471ad8d29a',	'7923a554-e15d-4ec0-a07f-436b9784d220',	'ac25bea0-cdca-4607-8ef7-2021afc62c09',	NULL,	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'That is a stupid thing to do'),
('e7e16bd6-f0bf-4b5e-b105-c98c2dd6459b',	'7923a554-e15d-4ec0-a07f-436b9784d220',	NULL,	'99f73c4c-5183-4721-94dc-73c255f4f243',	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'At least this is true'),
('57216669-ef0a-4ae0-ab40-2b9d965c22e3',	'7923a554-e15d-4ec0-a07f-436b9784d220',	NULL,	'1f0e3b8a-7ca7-44fb-8b95-dc5da95be412',	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'Haven''t found it yet =('),
('16375295-64dc-43d6-b91d-c43689a812c6',	'ce954774-f8ba-47d9-bd61-dcc6af173e2d',	'46597b48-38f7-467b-a1c3-58cdd00b58db',	NULL,	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'new comment'),
('cfc5ffc1-87b4-42ce-a1a9-b161d185503a',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	NULL,	'e86c95be-97be-4f12-bc94-bb14d7429cb7',	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'Huh, nice, seems to be working'),
('77e38958-f479-459c-beaa-256bf4927903',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	NULL,	'54e5b9f2-c4d2-44a5-9bf4-3c5e30cddaae',	'2020-10-30 00:00:00',	'2020-10-30 00:00:00',	'Good, user "Max" voted on the question and the first answer differently, and this answer still displays a vote interface for him, as well as the right score.');

INSERT INTO "votes" ("pk_vote", "fk_ownerid", "fk_questionid", "fk_answerid", "isupvote") VALUES
('4989ca81-8d26-47b4-ae96-6a20116e6648',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	NULL,	'a0a27a54-732b-49d0-8a96-01dd6df71fd1',	'1'),
('39dca38e-b475-459b-92d6-b9bbac271c94',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	NULL,	'1f0e3b8a-7ca7-44fb-8b95-dc5da95be412',	'0'),
('c4973e58-0d50-4a7e-81fc-da2a493b16fb',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	'46597b48-38f7-467b-a1c3-58cdd00b58db',	NULL,	'1'),
('871a0d2c-0b3b-4950-ba82-60a8c5d86389',	'191605e5-f95e-499e-abc4-c34935bc7aad',	'ac25bea0-cdca-4607-8ef7-2021afc62c09',	NULL,	'0'),
('15b1840c-e052-452f-819d-3264470212b6',	'191605e5-f95e-499e-abc4-c34935bc7aad',	'46597b48-38f7-467b-a1c3-58cdd00b58db',	NULL,	'1'),
('528fb956-7cdb-4d26-af58-58be03f86c14',	'7923a554-e15d-4ec0-a07f-436b9784d220',	NULL,	'99f73c4c-5183-4721-94dc-73c255f4f243',	'1'),
('43049bc7-9d7f-40ca-bdf2-8b3416164f14',	'7923a554-e15d-4ec0-a07f-436b9784d220',	'ac25bea0-cdca-4607-8ef7-2021afc62c09',	NULL,	'0'),
('a523c35d-e1b6-4a67-88e3-07e95f781ef8',	'7923a554-e15d-4ec0-a07f-436b9784d220',	'46597b48-38f7-467b-a1c3-58cdd00b58db',	NULL,	'1'),
('6d0a464b-1d7a-42d5-bbd3-8672801e415d',	'ce954774-f8ba-47d9-bd61-dcc6af173e2d',	'46597b48-38f7-467b-a1c3-58cdd00b58db',	NULL,	'0'),
('394d240c-671f-43d9-bcfa-844c99d71d41',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	'05d48428-ac57-4ed4-b242-4109be9ca2b6',	NULL,	'1'),
('2df200e2-fe50-4945-83e1-6e2b6b779b69',	'f44b34c6-978c-47d1-ae97-bbee20e200ff',	NULL,	'e86c95be-97be-4f12-bc94-bb14d7429cb7',	'0');
