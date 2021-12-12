package com.java.starhotel.dao;

public class CartEmptyException extends Exception {
	public CartEmptyException() {
		super("Cart is empty");
	}
}
