create table language_translation
(
	language_id int not null,
	translation_id int not null,
	constraint language_translation_language_id_fk
		foreign key (language_id) references language (id),
	constraint language_translation_translation_id_fk
		foreign key (translation_id) references translation (id)
);

create unique index language_translation_language_id_translation_id_uindex
	on language_translation (language_id, translation_id);

