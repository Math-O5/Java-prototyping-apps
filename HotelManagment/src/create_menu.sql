use hotelstar;

DROP TABLE IF EXISTS menu;

create table menu (id int not null auto_increment,
					name varchar(50), price float, active boolean, date Date, category varchar(50),
                    freeDelivery boolean, primary key(id)); 