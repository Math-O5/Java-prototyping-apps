package com.java.starhotel.dao;

public class CartEmptyException extends Exception {
	public CartEmptyException(String e) {
		super(e);
		System.out.println("Cart is empty.");
	}
}
