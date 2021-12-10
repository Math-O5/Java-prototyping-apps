package com.java.starhotel.model;

import java.util.List;
import java.util.Objects;

/**
 * 
 * @author mathiasf
 * @apiNote Cart will represent customer’s cart to hold the selected menu items
 */
public class Cart {
	private List<MenuItem> menuItemList;
	private double total;
	
	public Cart(List<MenuItem> menuItemList, double total) {
		super();
		this.menuItemList = menuItemList;
		this.total = total;
	}

	public List<MenuItem> getMenuItemList() {
		return menuItemList;
	}

	public void setMenuItemList(List<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Cart [menuItemList=" + menuItemList + ", total=" + total + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(menuItemList, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(menuItemList, other.menuItemList)
				&& Double.doubleToLongBits(total) == Double.doubleToLongBits(other.total);
	}
	
	
	
}
