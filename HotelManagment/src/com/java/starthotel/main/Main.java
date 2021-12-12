package com.java.starthotel.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.java.starhotel.dao.CartDaoCollectionlmpl;

public class Main {
	static List<String> actionsAdminMenu = new ArrayList<String>(
			Arrays.asList("add", "see", "see <:id> <active>", "edit <:id>", "delete <:id>"));
	
	static List<String> actionsUserMenu = new ArrayList<String>(
			Arrays.asList("see", "see <:id>"));
	
	static List<String> actionsAdminCart = new ArrayList<String>(
			Arrays.asList("add", "see", "edit <id>", "delete <id>"));
	
	static List<String> actionsUserCart = new ArrayList<String>(
			Arrays.asList("add", "see", "see <id>", "edit <id>", "delete <id>"));
	
	CartDaoCollectionlmpl cartDao = new CartDaoCollectionlmpl();


	private static void showCommands(List<String> commands) {
		for(String command : commands)
			System.out.println(command);
	}
	
	private static void msgEnterAs() {
		System.out.println("You would like to enter as:");
		System.out.println("0 to enter as Admin");
		System.out.println("1 to enter as Buyer");
	}
	
	private static void msgAdminPerfil() {
		System.out.println("You choose enter as Admin.");
		System.out.println("Enter your id:");
	}
	
	private static void msgUserPerfil() {
		System.out.println("You choose enter as client.");
		System.out.println("Enter your id:");
	}
	
	public static void hotelUser() {
		msgAdminPerfil();
		
//		while(true) {
//			switch (key) {
//			case 0: {
//				
//			}
//			default:
//				throw new IllegalArgumentException("Unexpected value: " + key);
//			}
//		}
	}
	
	public static void hotelAdmin() {
		msgUserPerfil() ;
		while(true) {
//			switch (key) {
//			case 0: {
//				
//			}
//			default:
//				throw new IllegalArgumentException("Unexpected value: " + key);
//			}
		}
	}
	
	public static void hotelHall() {
		String lineHolder = "";
		Scanner scan = new Scanner(System.in);
				
		while(true) {
			msgEnterAs();			
			int value = scan.nextInt();

			switch (value) {
			case 0: {
				hotelAdmin();
			}
			
			case 1: {
				hotelUser();
			}		
			default:
				break;
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
