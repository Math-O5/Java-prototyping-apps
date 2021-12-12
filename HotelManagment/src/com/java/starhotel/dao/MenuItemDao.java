package com.java.starhotel.dao;

import com.java.starhotel.model.MenuItem;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

public interface MenuItemDao {
	public abstract void addMenuItemAdmin(String name, double price, boolean active, String date, String category, boolean freeDelivery)  throws ParseException;
	public abstract List<MenuItem> getMenuItemListAdmin();
	public abstract List<MenuItem> getMenuItemListCustomer();
	public abstract void modifyMenuItem(MenuItem menuItem);
	public abstract MenuItem getMenuItem(int menuItemId);
}
