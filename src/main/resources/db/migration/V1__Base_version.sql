create table contexts (id bigint generated by default as identity, name varchar(50), version integer, taskuser_id bigint, primary key (id));

create table authorities (taskuser_id bigint not null, name varchar(255));

create table taskusers (id bigint generated by default as identity, fullname varchar(255), password varchar(255) not null, username varchar(255) not null, version integer, primary key (id));

create table projects (id bigint generated by default as identity, completeddate timestamp, is_done boolean, name varchar(100), startdate timestamp, version integer, 
	taskuser_id bigint, primary key (id));

create table tasks (id bigint generated by default as identity, completeddate timestamp, isdone boolean, 
	startdate timestamp, status varchar(255), title varchar(100), version integer, context_id bigint, taskuser_id bigint, project_id bigint, primary key (id));

alter table contexts add constraint FK_CONTEXTS_USERS foreign key (taskuser_id) references taskusers(id);

alter table authorities add constraint FK_AUTHORITIES_USERS foreign key (taskuser_id) references taskusers(id);

alter table projects add constraint FK_PROJECTS_USERS foreign key (taskuser_id) references taskusers(id);

alter table tasks add constraint FK_TASKS_CONTEXTS foreign key (context_id) references contexts;
alter table tasks add constraint FK_TASKS_PROJECTS foreign key (project_id) references projects;
alter table tasks add constraint FK_TASKS_USERS foreign key (taskuser_id) references taskusers(id);