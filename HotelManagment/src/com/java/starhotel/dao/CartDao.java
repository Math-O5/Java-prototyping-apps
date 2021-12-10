package com.java.starhotel.dao;

import com.java.starhotel.model.MenuItem;
import java.util.List;


public interface CartDao {
	public abstract void addCartItem(long userId, long meuItemId);
	public abstract List<MenuItem> getAllCartItems(long userId) throws CartEmptyException;
	public abstract void removeCartItem(long userId, long menuItemId);
}
