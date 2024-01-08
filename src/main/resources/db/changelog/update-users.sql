alter table Users add  actif boolean default false;
alter table Users add column validationcode varchar(10);
alter table user_examen add column resultat BIGINT;
ALTER TABLE user_examen ALTER resultat SET DEFAULT 0;
