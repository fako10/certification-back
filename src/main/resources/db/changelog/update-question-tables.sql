alter table question drop column correcte;
alter table reponse drop column correcte;
alter table reponse add column correcte boolean not null;