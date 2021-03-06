package com.java.starhotel.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.java.starhotel.model.Cart;
import com.java.starhotel.model.MenuItem;

public class CartDaoCollectionlmpl implements CartDao {
	/**
	 * This class manages the data related to Cart of all users of star-hotel
	 * application.
	 */
	private static HashMap<Integer, Cart> userCarts;

	public CartDaoCollectionlmpl() {
		if (userCarts == null)
			userCarts = new HashMap<Integer, Cart>();
	}

	/**
	 * 
	 * @return new Cart object empty.
	 */
	private Cart createCart() {
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		return new Cart(menuItemList, 0);
	}

	@Override
	public void addCartItem(int userId, int menuItemId) {
		Cart cart = null;
		MenuItemDaoCollectionlmpl menuItemDao;

		if (!userCarts.containsKey(userId)) {
			cart = createCart();
			userCarts.put(userId, cart);
		} else {
			cart = userCarts.get(userId);
		}

		menuItemDao = new MenuItemDaoCollectionlmpl();
		for (MenuItem menuItem : menuItemDao.getMenuItemListCustomer()) {
			if (menuItem.getId() == menuItemId) {
				updateCart(cart, menuItemId);
				System.out.println("Item " + menuItemId + " added to the cart user " + userId);
				return;
			}
		}

		System.out.println("Item not available");
	}

	@Override
	public List<MenuItem> getAllCartItems(int userId) throws CartEmptyException {
		Cart cart = null;

		if (!userCarts.containsKey(userId)) {
			cart = createCart();
			userCarts.put(userId, cart);
		} else {
			cart = userCarts.get(userId);
		}

		List<MenuItem> menuItemList = cart.getMenuItemList();

		if (menuItemList.isEmpty())
			throw new CartEmptyException();

		updateTotalCartPrice(cart, menuItemList);

		return menuItemList;
	}

	@Override
	public void removeCartItem(int userId, int menuItemId) {
		Cart cart = userCarts.get(userId);

		if (cart == null)
			return;

		List<MenuItem> menuList = cart.getMenuItemList();

		Iterator<MenuItem> itr = menuList.iterator();
		do {
			if (itr.next().getId() == menuItemId) {
				itr.remove();
				System.out.println("Item " + menuItemId + " removed from the cart user " + userId);
				break;
			}
		} while (itr.hasNext());

	}

	private void addItemToCart(Cart cart, MenuItem item) {
		List<MenuItem> menuItemList = cart.getMenuItemList();
		menuItemList.add(item);
		double total = cart.getTotal() + item.getPrice();
		cart.setTotal(total);
	}

	private void updateCart(Cart cart, int menuItemId) {
		MenuItem menuItem = null;

		MenuItemDaoCollectionlmpl menuItemDao = new MenuItemDaoCollectionlmpl();
		menuItem = menuItemDao.getMenuItem(menuItemId);
		addItemToCart(cart, menuItem);

	}

	private void updateTotalCartPrice(Cart cart, List<MenuItem> menuItemList) {
		double total = 0;

		for (MenuItem item : menuItemList) {
			total += item.getPrice();
		}

		cart.setTotal(total);
	}
	
	public double getTotal(int userId) throws CartEmptyException {
		Cart cart = userCarts.get(userId);
		
		if(cart == null) {
			userCarts.put(userId, createCart());
			throw new CartEmptyException();
		}
		return cart.getTotal();
	}
}
