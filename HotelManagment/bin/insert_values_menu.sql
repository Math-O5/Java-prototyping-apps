use hotelstar;
DELETE from menu;
INSERT INTO menu (name, price, ACTIVE, date, category, freeDelivery)
VALUES ("Sandwich", 99, true, '2017-03-15', "Main Course", true),
			("Burger", 129, true, '2019-09-21', "Main Course", false),
			("Pizza", 149, true, '2018-08-21', "Main Course", false),
			("French Fries", 57, true, '2017-07-02', "Starters", true),
			("Chocolate Brownie", 32, true,  '2019-09-21', "Dessert", true);
            
SELECT * FROM menu;