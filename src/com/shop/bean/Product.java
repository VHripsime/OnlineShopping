package com.shop.bean;

public class Product {
	private int productID;
	private String colour;
	private int size;

	protected int getProductID() {
		return productID;
	}

	protected void setProductID(int productID) {
		this.productID = productID;
	}

	protected String getColour() {
		return colour;
	}

	protected void setColour(String colour) {
		this.colour = colour;
	}

	protected int getSize() {
		return size;
	}

	protected void setSize(int size) {
		this.size = size;
	}
}
