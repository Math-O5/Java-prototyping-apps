package com.java.starhotel.dao.test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.java.starhotel.dao.MenuItemDaoCollectionlmpl;
import com.java.starhotel.model.MenuItem;
import com.java.starhotel.util.DateUtil;

class MenuItemDaoCollectionImplTest {

	@Test
	void testGetMenuItemListCustomer() {
		System.out.println("Custumer Getting Menu Item Test");
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		List<MenuItem> menuItemListDao = new ArrayList<MenuItem>();

		try {
			menuItemList.add(
					new MenuItem(0, "Sandwich", 99, true, DateUtil.convertToDate("15/03/2017"), "Main Course", true));
			menuItemList.add(
					new MenuItem(2, "Pizza", 149, true, DateUtil.convertToDate("21/08/2018"), "Main Course", false));
			menuItemList.add(
					new MenuItem(3, "French Fries", 57, true, DateUtil.convertToDate("02/07/2017"), "Starters", true));

			MenuItemDaoCollectionlmpl menuItemDao = new MenuItemDaoCollectionlmpl();
			for (MenuItem item : menuItemDao.getMenuItemListCustomer()) {
				System.out.println(item);
				menuItemListDao.add(item);
			}
			assertEquals(true, menuItemList.equals(menuItemListDao));
		} catch (ParseException e) {
			fail("Cannot instantiate");
			e.printStackTrace();
		}
	}

	@Test
	void testGetMenuItemListAdmin() {
		System.out.println("Admin Getting Menu Item Test");
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();

		Date today = new Date();
		Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));

		try {
			menuItemList.add(
					new MenuItem(0, "Sandwich", 99, true, DateUtil.convertToDate("15/03/2017"), "Main Course", true));
			menuItemList.add(new MenuItem(1, "Burger", 129, true, tomorrow, "Main Course", false));
			menuItemList.add(
					new MenuItem(2, "Pizza", 149, true, DateUtil.convertToDate("21/08/2018"), "Main Course", false));
			menuItemList.add(
					new MenuItem(3, "French Fries", 57, true, DateUtil.convertToDate("02/07/2017"), "Starters", true));
			menuItemList.add(new MenuItem(4, "Chocolate Brownie", 32, true, tomorrow, "Dessert", true));

			MenuItemDaoCollectionlmpl menuItemDao = new MenuItemDaoCollectionlmpl();
			int i = 0;

			for (MenuItem item : menuItemDao.getMenuItemListAdmin()) {
				System.out.println(item);
				assertEquals(menuItemList.get(i).getId(), item.getId());
				assertEquals(menuItemList.get(i).getNameString(), item.getNameString());
				i += 1;
			}
		} catch (ParseException e) {
			fail("Cannot instantiate");
			e.printStackTrace();
		}
	}

	@Test
	void testModifyMenuItem() {
		System.out.println("Admin Modifying Menu Item Test");

		Date today = new Date();
		Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));

		MenuItemDaoCollectionlmpl menuItemDao = new MenuItemDaoCollectionlmpl();
		MenuItem menuItem = menuItemDao.getMenuItem(2);
		System.out.println(menuItem.toString());
		menuItem.setNameString("Empanadas Stars");
		menuItemDao.modifyMenuItem(menuItem);

		MenuItem menuItemEdited = menuItemDao.getMenuItem(2);
		assertEquals(menuItem, menuItemEdited);
		System.out.println(menuItem.toString());
	}

}
