create table taskstatuses (id bigserial, taskstatus varchar(255), primary key(id));
insert into taskstatuses (taskstatus) values ('opened');
insert into taskstatuses (taskstatus) values ('assigned');
insert into taskstatuses (taskstatus) values ('in progress');
insert into taskstatuses (taskstatus) values ('testing');
insert into taskstatuses (taskstatus) values ('completed');

create table users (id bigserial, surname varchar(255), name varchar(255), middlename varchar(255), primary key(id));
insert into users (surname, name, middlename) values ('Ivanov', 'Ivan', 'Ivanovich');
insert into users (surname, name, middlename) values ('Petrov', 'Petr', 'Petrovich');
insert into users (surname, name, middlename) values ('Sidorov', 'Sidor', 'Sidorovich');

create table tasks (id bigserial, title varchar(255), owner_id integer REFERENCES users (id), executer_id integer REFERENCES users (id), description varchar(255),status_id integer REFERENCES taskstatuses (id), primary key(id));

create table userroles (id bigserial, rolename varchar(255), primary key(id));
insert into userroles (rolename) values ('user');
insert into userroles (rolename) values ('admin');





