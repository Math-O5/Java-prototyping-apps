package com.java.starhotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.java.starhotel.model.Cart;
import com.java.starhotel.model.MenuItem;

public class CartDaoSqllmpl implements CartDao {

	@Override
	public void addCartItem(int userId, int menuItemId) {

		// get Menu Item
		MenuItemDaosqllmpl menuSql = new MenuItemDaosqllmpl();
		MenuItem item = menuSql.getMenuItem(menuItemId);

		if (item == null)
			return;

		// Check if item already exist
		String sqlItemInf = "SELECT * FROM cart WHERE userId=" + userId + " AND menuId=" + menuItemId;
		List<Integer> itemsQuantity = new ArrayList<>();
		List<Integer> itemsId = new ArrayList<>();

		try {
			ResultSet result = ConnectionHandler.executeQueryStatement(sqlItemInf);
			while (result.next()) {
				itemsId.add(result.getInt(2));
				itemsQuantity.add(result.getInt(3));
			}
		} catch (SQLException e) {
			System.out.println("Cart item must be created");
			insertCart(userId, menuItemId, 1);			
		}

		if (itemsId.isEmpty()) {
			System.out.println("Cart item created");
			insertCart(userId, menuItemId, 1);			
		} else {
			for (int i = 0; i < itemsId.size(); ++i) {
				System.out.println("Cart updated");
				updateCart(userId, menuItemId, itemsQuantity.get(i)+1);
			}
		}
	}

	private static void updateCart(int userId, int menuItemId, int quantity) {
		String sqlStatement = "UPDATE cart " + "SET quantity=" + quantity + " " + "WHERE userId=" + userId
				+ " AND menuId=" + menuItemId;
		Statement stmt = null;
		try {
        	Connection con = ConnectionHandler.getConnection();
        	stmt = con.createStatement();
        	stmt.executeUpdate(sqlStatement);  
        } catch (SQLException e) {
            System.out.println(e);
        } 
	}

	private static void insertCart(int userId, int menuItemId, int quantity) {
		String sqlStatement = "INSERT INTO cart (userId, menuId, quantity) " + "VALUES(" + userId + ", " + menuItemId
				+ ", " + quantity + ")";

		try {
			Connection con = ConnectionHandler.getConnection();
			PreparedStatement pre = con.prepareStatement(sqlStatement);
			pre.execute();
			pre.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MenuItem> getAllCartItems(int userId) throws CartEmptyException {
		String sqlGetCart = "SELECT * FROM cart WHERE userId=" + userId;
		// get Menu Item
		MenuItemDaosqllmpl menuSql = new MenuItemDaosqllmpl();
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		MenuItem menuItem;

		// Check if item already exist
		List<Integer> itemsQuantity = new ArrayList<>();
		List<Integer> itemsId = new ArrayList<>();

		try {
			ResultSet result = ConnectionHandler.executeQueryStatement(sqlGetCart);
		
			while (result.next()) {
				itemsId.add(result.getInt(2));
				itemsQuantity.add(result.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (itemsId.isEmpty())
			throw new CartEmptyException();

		for (int i = 0; i < itemsId.size(); ++i) {
			menuItem = menuSql.getMenuItem(itemsId.get(i));
			for (int k = 0; k < itemsQuantity.get(i); ++k)
				menuItemList.add(menuItem);
		}

		return menuItemList;
	}

	@Override
	public void removeCartItem(int userId, int menuItemId) {
		MenuItemDaosqllmpl menuSql = new MenuItemDaosqllmpl();
		MenuItem item = menuSql.getMenuItem(menuItemId);

		if (item == null)
			return;

		// Check if item already exist
		String sqlItemInf = "SELECT * FROM cart WHERE userId=" + userId + " AND menuId=" + menuItemId;
		List<Integer> itemsQuantity = new ArrayList<>();
		List<Integer> itemsId = new ArrayList<>();

		try {
			ResultSet result = ConnectionHandler.executeQueryStatement(sqlItemInf);
			while (result.next()) {
				itemsId.add(result.getInt(2));
				itemsQuantity.add(result.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (itemsId.isEmpty()) {
			return;
		} else {
			for (int i = 0; i < itemsId.size(); ++i) {
				int quantity = itemsQuantity.get(i);
				if(quantity-1 <= 0) {
					String sqlItemDrop = "DELETE FROM cart WHERE userId=" + userId + " AND menuId=" + menuItemId;
					Connection con;
					try {
						con = ConnectionHandler.getConnection();
						PreparedStatement pre = con.prepareStatement(sqlItemDrop);
						pre.execute();
						pre.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				updateCart(userId, menuItemId, quantity-1);
			}
		}
	}
	
	public double getTotal(int userId) throws CartEmptyException {
		String sql = "SELECT * FROM cart WHERE userID=" + userId;
		
		// get Menu Item
		MenuItemDaosqllmpl menuSql = new MenuItemDaosqllmpl();


		double total = 0.0;
		int count = 0;
		
		try {
			ResultSet result = ConnectionHandler.executeQueryStatement(sql);
			while(result.next()) {
				MenuItem item = menuSql.getMenuItem(result.getInt(2));
				if (item == null)
					continue;
				int quantity = result.getInt(3);
				total += quantity * item.getPrice();
				count += quantity;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return total;
	}

}
