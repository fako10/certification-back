drop table if exists user_examen_question;
drop table if exists user_examen_reponse;
CREATE TABLE user_examen_question
(
    id               BIGINT primary key,
    question_id      BIGINT,
    user_examen_id   BIGINT,
    FOREIGN KEY(question_id) REFERENCES question(id),
    FOREIGN KEY(user_examen_id) REFERENCES user_examen(id)
);

create TABLE user_examen_reponse
(
id                               BIGINT primary key,
    user_examen_question_id      BIGINT,
    reponse_id                   BIGINT,
    isSelected                   BOOLEAN,
    FOREIGN KEY(user_examen_question_id) REFERENCES user_examen_question(id),
    FOREIGN KEY(reponse_id) REFERENCES reponse(id)
);

drop SEQUENCE user_examen_question_sequence;
drop sequence user_examen_reponse_sequence;
CREATE SEQUENCE user_examen_question_sequence START 1;
CREATE SEQUENCE user_examen_reponse_sequence START 1;

alter table user_examen add column nbre_questions BIGINT;

