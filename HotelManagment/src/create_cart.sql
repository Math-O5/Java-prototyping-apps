use hotelstar;

DROP TABLE IF EXISTS cart;
CREATE TABLE cart (userId int, menuId int, quantity int default 0, primary key(userId, menuId)); 
