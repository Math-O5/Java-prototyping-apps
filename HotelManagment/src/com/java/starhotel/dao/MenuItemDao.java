package com.java.starhotel.dao;

import com.java.starhotel.model.MenuItem;
import java.util.List;

public interface MenuItemDao {
	public abstract List<MenuItem> getMenuItemListAdmin();
	public abstract List<MenuItem> getMenuItemListCustomer();
	public abstract void modifyMenuItem(MenuItem menuItem);
	public abstract MenuItem getMenuItem(long menuItemId);
}
