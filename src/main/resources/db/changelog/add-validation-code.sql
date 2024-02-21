--alter table users add column validationcode VARCHAR(255);
--alter table user_examen  add column nbre_questions_correcte	 INTEGER;
--alter table examen  add column pourcentage_reussite	 INTEGER;
--alter table examen add column duree Integer


--CREATE SEQUENCE payement_sequence START 1;
--create TABLE payement
--(
--    id                               BIGINT primary key,
--    libelle                          BIGINT,
--    certification_id                 BIGINT,
--    user_id                          BIGINT,
--   currency                         varchar(10),
--  successUrl                       varchar(255),
-- cancelUrl                        varchar(255),
-- amount                           float,
-- payement_date					 date,
-- FOREIGN KEY(certification_id) REFERENCES Certification(id),
--FOREIGN KEY(user_id) REFERENCES Users(id)

--);

-- Alter table certification add column amount float