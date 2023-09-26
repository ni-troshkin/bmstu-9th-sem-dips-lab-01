create table public.person (
	id_person bigserial not null,
	name text not null,
	age int4,
	address text,
	work text
);

alter table public.person
add constraint pk_person primary key (id_person);

alter table public.person
add constraint positive_age check (age >= 0);
