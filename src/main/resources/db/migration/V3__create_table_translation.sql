create table translation
(
	id int auto_increment,
	`key` varchar(50) not null,
	meaning varchar(255) not null,
	constraint translation_pk
		primary key (id)
);

create unique index translation_key_uindex
	on translation (`key`);

create unique index translation_meaning_uindex
	on translation (meaning);

