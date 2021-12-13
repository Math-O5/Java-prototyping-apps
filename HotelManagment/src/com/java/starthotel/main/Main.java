package com.java.starthotel.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.java.starhotel.dao.CartDaoCollectionlmpl;
import com.java.starhotel.dao.CartEmptyException;
import com.java.starhotel.dao.MenuItemDaoCollectionlmpl;
import com.java.starhotel.model.MenuItem;

public class Main {
	static HashMap<String, Integer> commandsAdmin = new HashMap<String, Integer>();
	static HashMap<String, Integer> commandsUser = new HashMap<String, Integer>();

	static List<String> actionsAdminMenu = new ArrayList<String>(Arrays.asList("add : to add an item into Menu",
			"see : to see menu", "edit :to edit the menu", "delete : to delete the menu", "exit : to go back"));

	static List<String> actionsUserMenu = new ArrayList<String>(
			Arrays.asList("see : to see the menu", "exit : to go back"));

	static List<String> actionsAdminCart = new ArrayList<String>(
			Arrays.asList("add : to add an item to the cart", "see : to see the items in the cart",
					"total : to see the total yet", "delete : to delete an item from the cart", "exit : to go back"));

	static List<String> actionsUserCart = new ArrayList<String>(Arrays.asList("add : to add an item to the cart",
			"see : to see the items in the cart", "delete : to delete an item from the cart", "exit : to go back"));

	static CartDaoCollectionlmpl cartDao = new CartDaoCollectionlmpl();
	static MenuItemDaoCollectionlmpl menuItem = new MenuItemDaoCollectionlmpl();

	static Scanner scan = new Scanner(System.in);

	private static void showCommands(List<String> commands) {
		System.out.println("Your actions are:");
		for (String command : commands)
			System.out.println(command);
	}

	private static void msgEnterAs() {
		System.out.println("You would like to enter as:");
		System.out.println("0 to enter as Admin");
		System.out.println("1 to enter as Buyer");
		System.out.println("Any number to exit");
	}

	private static void msgAdminPerfil() {
		System.out.println("You choose enter as Admin.");
		System.out.println("Enter your id:");
		System.out.println("Enter -1 to leave.");
	}

	private static void msgUserPerfil() {
		System.out.println("You choose enter as client.");
		System.out.println("Enter your id:");
		System.out.println("Enter -1 to leave.");
	}

	public static void editCart(int userId) {

		String command = "";
		Integer key;

		commandsAdmin.put("add", 0);
		commandsAdmin.put("see", 1);
		commandsAdmin.put("total", 2);
		commandsAdmin.put("delete", 3);
		commandsAdmin.put("exit", 4);

		while (true) {
			showCommands(actionsUserCart);
			command = scan.next();

			if (commandsAdmin.containsKey(command)) {
				key = commandsAdmin.get(command);
			} else {
				System.out.println("Command invalid.");
				continue;
			}

			switch (key) {
			case 0: {
				System.out.println("Tell the id you want to insert.");
				List<MenuItem> list = menuItem.getMenuItemListCustomer();

				for (MenuItem item : list) {
					System.out.println(item.toString());
				}

				key = scan.nextInt();
				cartDao.addCartItem(userId, key);
				break;
			}
			case 1: {
				try {
					List<MenuItem> list = cartDao.getAllCartItems(userId);

					for (MenuItem item : list) {
						System.out.println(item.toString());
					}

				} catch (CartEmptyException e) {
					System.out.println("Your cart is empty!");
				}
				break;
			}
			case 2: {
				try {
					System.out.println("Total in cart: " + cartDao.getTotal(userId));
				} catch (CartEmptyException e) {
					e.printStackTrace();
				}
				break;
			}
			case 3: {
				System.out.println("Tell the id you want to remove.");

				try {
					List<MenuItem> list = cartDao.getAllCartItems(userId);

					for (MenuItem item : list) {
						System.out.println(item.toString());
					}

					key = scan.nextInt();
					cartDao.removeCartItem(userId, key);
				} catch (CartEmptyException e) {
					System.out.println("You have not itme to delete.");
				}
				break;

			}
			default:
				System.out.println("Section closed.");
				return;
			}
		}

	}

	public static void editAdminMenu(int userId) {
		showCommands(actionsAdminMenu);
	}

	public static void hotelAdmin() {
		Integer key;

		while (true) {
			msgAdminPerfil();

			key = scan.nextInt();

			if (key == -1) {
				System.out.println("Log out.");
				return;
			}
			try {
				cartDao.getTotal(key);
				Integer userId = key;

				System.out.println("0 : Enter into menu.");
				System.out.println("1 : Enter into cart.");
				System.out.println("2 : Leave.");
				key = scan.nextInt();
				switch (key) {
				case 0: {
					editAdminMenu(userId);
					break;
				}
				case 1: {
					editCart(userId);
					break;
				}
				case 2: {
					return;
				}
				default:
					System.out.println("Command invalid.");
					break;
				}
			} catch (CartEmptyException e) {
				System.out.println("Creating cart, now you can add items!");
			}
		}
	}

	public static void hotelHall() {
		Integer key;

		while (true) {
			msgUserPerfil();

			key = scan.nextInt();

			if (key == -1) {
				System.out.println("Log out.");
				return;
			}
			try {
				cartDao.getTotal(key);
				Integer userId = key;

				System.out.println("0 : See the menu.");
				System.out.println("1 : Enter into cart.");
				System.out.println("2 : Leave.");
				key = scan.nextInt();
				switch (key) {
				case 0: {
					List<MenuItem> list = menuItem.getMenuItemListCustomer();
					
					for(MenuItem item : list) {
						System.out.println(item.toString());
					}
					break;
				}
				case 1: {
					editCart(userId);
					break;
				}
				case 2: {
					return;
				}
				default:
					System.out.println("Command invalid.");
					break;
				}
			} catch (CartEmptyException e) {
				System.out.println("Creating cart, now you can add items!");
			}
		}
	}
	

	public static void main(String[] args) {
		System.out.println("============= Welcome do Start Hotel  ====================================");
		System.out.println("============= Please feel free to exit typeing 'exit' or 'bye' ===========");
		hotelHall();
		System.out.println("============= We waiting for you next visit. =============================");
	}

}
