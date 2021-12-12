use hotelstar;

DROP TABLE IF EXISTS user;

create table user (id int not null auto_increment, password varchar(50), isAdmin boolean, primary key(id));