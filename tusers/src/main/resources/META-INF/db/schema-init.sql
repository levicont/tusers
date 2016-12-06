drop table if exists tusers.persistent_logins; 
create table tusers.persistent_logins (username varchar(64) not null,
								series varchar(64) primary key,
								token varchar(64) not null,
								last_used timestamp not null);
alter table tusers.persistent_logins owner to tusers;


drop table if exists tusers.user cascade; 
drop table if exists tusers.gallery cascade;
drop table if exists tusers.image cascade; 


create table tusers.user (	id_user bigint primary key,
							version int not null default 0,
							name text,
							surname text,
							email text,
							birth_date date,
							password text,
							info text);
alter table tusers.user owner to tusers;

 
create table tusers.gallery (id_gallery bigint primary key,
							version int not null default 0,
							name text,
							id_user bigint REFERENCES tusers.user (id_user) on delete cascade);
alter table tusers.gallery owner to tusers;


create table tusers.image (	id_image bigint primary key,
							version int not null default 0,
							id_gallery bigint REFERENCES tusers.gallery (id_gallery) on delete cascade,
							name text,
							src bytea);
alter table tusers.image owner to tusers;

drop sequence if exists tusers.user_id_seq;
drop sequence if exists tusers.image_id_seq;
drop sequence if exists tusers.gallery_id_seq;

create sequence tusers.user_id_seq start 1;
create sequence tusers.image_id_seq start 1;
create sequence tusers.gallery_id_seq start 1;

alter sequence tusers.user_id_seq owner to tusers;
alter sequence tusers.image_id_seq owner to tusers;
alter sequence tusers.gallery_id_seq owner to tusers;