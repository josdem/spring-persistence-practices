--liquibase formatted sql

--changeset makingdevs:18
alter table project add column full_description varchar(255);

--changeset makingdevs:19
update project set full_description=description where id=id and 1=1;

--changeset makingdevs:20
alter table project drop column description;