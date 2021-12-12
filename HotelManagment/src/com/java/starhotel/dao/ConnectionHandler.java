package com.java.starhotel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionHandler {
	private static String url = "jdbc:mysql://localhost:3306/";
	private static String nameDB = "hotelstar ";
	private static String user = "root";
	private static String password = "root";
	private static Connection connection;
	
	
	private ConnectionHandler() {
		throw new IllegalStateException("This class cannot be instantiate.");
	}
	
	public static Connection getConnection() throws SQLException {	
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url+nameDB, user, password);			
		}
		return connection;
	}
	
	public static void createTableMenu(boolean reset) throws SQLException {
		String sqlCreateMenu = "CREATE TABLE menu (id int not null auto_increment,"
				+ "name varchar(50), price float, active boolean, date Date, category varchar(50),"
				+ "freeDelivery boolean, primary key(id))";
		
		String drop = "DROP TABLE IF EXISTS menu ";
		if(reset == true) {
			executeUpdateQuery(drop + sqlCreateMenu);			
		} else {
			executeUpdateQuery(sqlCreateMenu);			
		}
		
		System.out.println("Created Menu List Table.");
	}
	
	
	public static void createTableCart(boolean reset) throws SQLException {
		String sqlCreateCart = "CREATE TABLE cart (userId int, menuId int, quantity int default 0, primary key(userId, menuId))";
		
		String drop = "DROP TABLE IF EXISTS cart ";
		if(reset == true) {
			executeUpdateQuery(drop + sqlCreateCart);			
		} else {
			executeUpdateQuery(sqlCreateCart);			
		}
		
		System.out.println("Created Cart Table.");
	}
	
	public static void runMigrations() throws SQLException {
		createTableCart(false);
		createTableMenu(false);
	}
	
   public static ResultSet executeQueryStatement(String sqlCommand) throws SQLException {
        Connection con = ConnectionHandler.getConnection();
        Statement statment = con.createStatement();
        ResultSet result = statment.executeQuery(sqlCommand);
        return result;
    }
	
	public static void executeUpdateQuery(String sqlCommand) throws SQLException {
		Connection con = getConnection();
		Statement statment = connection.createStatement();
		
		try {
			statment.executeQuery(sqlCommand);			
		}  catch(SQLException e) {
			e.printStackTrace();
		} finally {
			statment.close();						
		}
	}
	

}