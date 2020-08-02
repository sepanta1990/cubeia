create table language
(
	id int auto_increment,
	code varchar(10) not null,
	name varchar(100) not null,
	constraint language_pk
		primary key (id)
);

create unique index language_code_uindex
	on language (code);

