package com.java.starhotel.dao;

import com.java.starhotel.model.MenuItem;
import com.java.starhotel.util.DateUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MenuItemDaoCollectionlmpl implements MenuItemDao {
	private static List<MenuItem> menuItemList = null;
	
	public  MenuItemDaoCollectionlmpl() throws ParseException {
		if(menuItemList == null) {
			menuItemList = new ArrayList<MenuItem>();
			menuItemList.add(new MenuItem(0, "Sandwich", 99, true, DateUtil.convertToDate("15/03/2017"), "Main Course", true));
			menuItemList.add(new MenuItem(1, "Burger", 129, true, DateUtil.convertToDate("23/12/2017"), "Main Course", false));
			menuItemList.add(new MenuItem(2, "Pizza", 149, true, DateUtil.convertToDate("21/08/2018"), "Main Course", false));
			menuItemList.add(new MenuItem(3, "French Fries", 57, true, DateUtil.convertToDate("02/07/2017"), "Starters", true));
			menuItemList.add(new MenuItem(4, "Chocolate Brownie", 32, true, DateUtil.convertToDate("02/11/2022"), "Dessert", true));
		}
	}
	
	/**
	 * @Returns List of MenuItem which are active and already launched.
	 */
	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		List<MenuItem> customerMenuList = new ArrayList<MenuItem>();
		for(MenuItem item : menuItemList) {
			if(item.isActive() || DateUtil.isPastDate(item.getDateOfLaunch())) {
				customerMenuList.add(item);
			}
		}
		return customerMenuList;
	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		return menuItemList;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		for(int i = 0; i < menuItemList.size(); ++i) {
			if(menuItemList.get(i).getId() == menuItem.getId()) {
				menuItemList.set(i, menuItem);
				break;
			}
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		return menuItemList.get((int) menuItemId);
	}
}
