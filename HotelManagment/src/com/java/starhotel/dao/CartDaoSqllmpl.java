package com.java.starhotel.dao;

import java.util.List;

import com.java.starhotel.model.MenuItem;

public class CartDaoSqllmpl implements CartDao {

	@Override
	public void addCartItem(int userId, int meuItemId) {
		
		// get Menu Item
		// get price
		
		// Check if item already exist
		String sqlItemInf = "SELECT * FROM cart WHERE userId=? AND menuItemId=?";
		
		// 
		String sqlAddItem = "INSERT INTO cart () "
				+ "VALUES(?, ?, ?, ?)";
		
	}

	@Override
	public List<MenuItem> getAllCartItems(int userId) throws CartEmptyException {
		String sqlGetCart = "SELECT * FROM cart WHERE userId=?";
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCartItem(int userId, int menuItemId) {
		// TODO Auto-generated method stub
		
	}
	
}
