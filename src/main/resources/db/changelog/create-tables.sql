drop table if exists users;
CREATE TABLE users
(
    id bigint NOT NULL,
    role VARCHAR(255 ),
    email VARCHAR(255 ),
    enabled boolean NOT NULL  ,
    locked boolean NOT NULL,
    name VARCHAR(255 ),
    password VARCHAR(255 ),
    username VARCHAR(255 ),
    CONSTRAINT app_user_pkey PRIMARY KEY (id)
    );

drop table if exists certification;

CREATE TABLE certification
(
    id               BIGINT primary key,
    libelle          VARCHAR(255 ),
    description      VARCHAR(255 )
);


drop table if exists certification_user;
CREATE TABLE certification_user
(
    id               BIGINT primary key,
    user_id          BIGINT,
    certification_id BIGINT,
    date_expiration  date,
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(certification_id) REFERENCES certification(id)
);

drop table if exists examen;
CREATE TABLE examen
(
    id               BIGINT primary key,
    libelle          VARCHAR(255 ),
    description      VARCHAR(255 ),
    certification_id BIGINT,
    FOREIGN KEY(certification_id) REFERENCES certification(id)
);


drop table if exists question;
CREATE TABLE question
(
    id               BIGINT primary key,
    intitule         VARCHAR(255 ),
    description      VARCHAR(255 ),
    type_reponse     VARCHAR(255 ),
    correcte         boolean NOT NULL,
    examen_id BIGINT,
    FOREIGN KEY(examen_id) REFERENCES examen(id)
);

CREATE TABLE reponse
(
    id               BIGINT primary key,
    intitule         VARCHAR(255 ),
    description      VARCHAR(255 ),
    correcte         VARCHAR(255 ),
    question_id       BIGINT,
    FOREIGN KEY(question_id) REFERENCES question(id)
);

CREATE TABLE user_Examen(
    id               BIGINT primary key,
    examen_id        BIGINT,
    user_id          BIGINT,
    dateExamen       date,
    FOREIGN KEY(examen_id) REFERENCES examen(id),
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE Reponse_question(
    id               BIGINT primary key,
    question_id      BIGINT,
    user_Examen_id   BIGINT,
    reponse_id       BIGINT,
    FOREIGN KEY(user_Examen_id) REFERENCES user_Examen(id),
    FOREIGN KEY(question_id) REFERENCES question(id)
);



CREATE SEQUENCE users_sequence START 1;
CREATE SEQUENCE certification_sequence START 1;
CREATE SEQUENCE certification_user_sequence START 1;
CREATE SEQUENCE examen_sequence START 1;
CREATE SEQUENCE question_sequence START 1;
CREATE SEQUENCE reponse_sequence START 1;
CREATE SEQUENCE user_Examen_sequence START 1;
CREATE SEQUENCE Reponse_question_sequence START 1;