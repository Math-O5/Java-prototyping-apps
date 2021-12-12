package com.java.starhotel.dao.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.java.starhotel.dao.CartDaoCollectionlmpl;
import com.java.starhotel.model.MenuItem;

class CartDaoCollectionlmplTest {

//	@Test
//	void testGetAllCartItems() {
//		CartDaoCollectionlmpl cartDao = new CartDaoCollectionlmpl();
//		
//		try {
//			cartDao.addCartItem(1, 1);
//			List<MenuItem> menuList = cartDao.getAllCartItems(1);
//			for(MenuItem item : menuList) {
//				System.out.println(item.toString());
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			fail("Could not add item to cart");
//		}
//	}
	
	@Test
	void testAddCartItem() {
		CartDaoCollectionlmpl cartDao = new CartDaoCollectionlmpl();
		
		try {
			cartDao.addCartItem(1, 1);
			List<MenuItem> menuList = cartDao.getAllCartItems(1);
			for(MenuItem item : menuList) {
				System.out.println(item.toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
			fail("Could not add item to cart");
		}
	}

}
