insert into tusers.user (id_user, name, surname, email, birth_date, password)
	values (nextval('user_id_seq'), 'Vito', 'Rocky', 'vito@d.com', '1990-12-30', 
			'$2a$06$YFDV3nREma2uKmPIG/kEX.I2Qwur4NzRlcAtcWTglJIp7hvQ0rEuy');
insert into tusers.gallery(id_gallery, name, id_user)
	values(nextval('gallery_id_seq'), 'Default gallery', 1)

