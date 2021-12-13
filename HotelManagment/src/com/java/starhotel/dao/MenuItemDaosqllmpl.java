package com.java.starhotel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.java.starhotel.model.MenuItem;
import com.java.starhotel.util.DateUtil;

public class MenuItemDaosqllmpl implements MenuItemDao {
	private String url = "jdbc:mysql://localhost:3306/";
	private String nameDB = "hotelstar ";
	private String user = "root";
	private String password = "root";
	private Connection connection;
	
	public MenuItemDaosqllmpl() {
	}
	
	@Override
	public void addMenuItemAdmin(String name, double price, boolean active, String date, String category, boolean freeDelivery)  throws ParseException {
		String sql = "INSERT INTO menu (name, price, ACTIVE, date, category, freeDelivery) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		Date utilDate = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(date);
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		try {
			connection = ConnectionHandler.getConnection();
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, name);
			statment.setDouble(2, price);
			statment.setBoolean(3, active);
			statment.setDate(4, sqlDate);
			statment.setString(5, category);
			statment.setBoolean(6, freeDelivery);
			statment.executeUpdate();
			statment.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		String sql = "SELECT * FROM menu";
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		
		try {
			ResultSet result = ConnectionHandler.executeQueryStatement(sql);
			
			while(result.next()) {
				int id = result.getInt(0);
				String name = result.getString(1);
				double price = result.getDouble(2);
				boolean active = result.getBoolean(3);
				Date date = result.getDate(4);
				String category = result.getString(5);
				boolean freeDelivery = result.getBoolean(6);
				menuItemList.add(new MenuItem(id, name, price, active, date, category, freeDelivery));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuItemList;			
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		String sql = "SELECT * FROM menu WHERE active=true AND date <= CURDATE()";
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
//		int id = 1;
		try {
			ResultSet result = ConnectionHandler.executeQueryStatement(sql);
			
			while(result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				double price = result.getDouble(3);
				boolean active = result.getBoolean(4);
				Date date = result.getDate(5);
				String category = result.getString(6);
				boolean freeDelivery = result.getBoolean(7);
				menuItemList.add(new MenuItem(id, name, price, active, date, category, freeDelivery));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuItemList;		
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		String sqlStatement = "UPDATE menu " 
				+ "SET name=?, price=?, active=?, date=?, category=?, freeDelivery=? "
				+ "WHERE id = ?";

		try {
			Connection con = ConnectionHandler.getConnection();
			PreparedStatement pstm = con.prepareStatement(sqlStatement);
			pstm.setString(1, menuItem.getNameString());
			pstm.setDouble(2, menuItem.getPrice());
			pstm.setBoolean(3, menuItem.isActive());
			pstm.setDate(4, new Date(menuItem.getDateOfLaunch().getTime()));
			pstm.setString(5, menuItem.getCategory());
			pstm.setBoolean(6, menuItem.isFreeDelivery());
			pstm.setLong(7, menuItem.getId());
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MenuItem getMenuItem(int menuItemId) {
		String sql = "SELECT * FROM menu "
				+ "WHERE id=" + menuItemId;
		MenuItem menuItem = null;
		
		try {
			ResultSet result = ConnectionHandler.executeQueryStatement(sql);
			while(result.next()) {
				menuItem = new MenuItem(result.getInt(1),
						result.getString(2),
						result.getDouble(3),
						result.getBoolean(4),
						result.getDate(5),
						result.getString(6),
						result.getBoolean(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return menuItem;
	}
	

}
