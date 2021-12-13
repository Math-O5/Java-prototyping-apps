package com.java.starhotel.dao.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.java.starhotel.dao.CartDaoCollectionlmpl;
import com.java.starhotel.dao.CartEmptyException;
import com.java.starhotel.model.MenuItem;

class CartDaoCollectionlmplTest {
	
	@Test
	void testAddCartItem() {
		System.out.println("Add Item Cart Test");
		CartDaoCollectionlmpl cartDao = new CartDaoCollectionlmpl();
		
		try {
			cartDao.addCartItem(1, 2);
			List<MenuItem> menuList = cartDao.getAllCartItems(1);
			for(MenuItem item : menuList) {
				System.out.println(item.toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
			fail("Could not add item to cart");
		}
	}
	
	@Test
	void testRemoveCartItem() {
		System.out.println("Remove Item Cart Test");
		CartDaoCollectionlmpl cartDao = new CartDaoCollectionlmpl();
		
		try {
			cartDao.removeCartItem(1, 2);	
			cartDao.getAllCartItems(1);
			fail("Not empty");
		} catch(CartEmptyException e) {
			System.out.println("Cart is empty now.");
		}
		
	}

}
