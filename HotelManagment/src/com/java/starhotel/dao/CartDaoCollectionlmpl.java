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
	 * This class manages the data related to Cart of all users of star-hotel application.
	 */
	private static HashMap<Long, Cart> userCarts = null;
	
	public void CartdaoCollectionlmpl() {
		if(userCarts == null)
			userCarts = new HashMap<Long, Cart>();			
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
	public void addCartItem(long userId, long menuItemId) {
	
		Cart cart = userCarts.get(userId);			
		
		if(cart == null)
			cart = createCart();

		updateCart(cart, menuItemId);			
		System.out.println("Item " + menuItemId + " added to the cart user " + userId);		
	}
	

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		Cart cart = userCarts.get(userId);
		
		List<MenuItem> menuItemList = cart.getMenuItemList();
		
		if(menuItemList.isEmpty())
			throw new CartEmptyException("Cart is empty");
		
		updateTotalCartPrice(cart, menuItemList);
		
		return menuItemList;
	}
	

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		Cart cart = userCarts.get(userId);			
		
		if(cart == null)
			return;
		
		List<MenuItem> menuList = cart.getMenuItemList();
		
		Iterator<MenuItem> itr = menuList.iterator();
		do {
			if(itr.next().getId() == menuItemId) {
				itr.remove();
				System.out.println("Item " + menuItemId + " removed from the cart user " + userId);		
			}
		} while(itr.hasNext());
		
	}
	
	private void addItemToCart(Cart cart, MenuItem item) {
		List<MenuItem> menuItemList = cart.getMenuItemList();
		menuItemList.add(item);
		double total = cart.getTotal() + item.getPrice();
		cart.setTotal(total);
	}
	
	private void updateCart(Cart cart, long menuItemId) {
		MenuItem menuItem = null;
		
		try {
			MenuItemDaoCollectionlmpl menuItemDao = new MenuItemDaoCollectionlmpl();
			menuItem = menuItemDao.getMenuItem(menuItemId);
			addItemToCart(cart, menuItem);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void updateTotalCartPrice(Cart cart, List<MenuItem> menuItemList) {
		double total = 0;

		for(MenuItem item : menuItemList) {
			total += item.getPrice();
		}
		
		cart.setTotal(total);
	}
}
