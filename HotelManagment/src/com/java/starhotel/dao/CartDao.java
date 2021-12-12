package com.java.starhotel.dao;

import com.java.starhotel.model.MenuItem;
import java.util.List;


public interface CartDao {
	public abstract void addCartItem(int userId, int meuItemId);
	public abstract List<MenuItem> getAllCartItems(int userId) throws CartEmptyException;
	public abstract void removeCartItem(int userId, int menuItemId);
}
