package com.scalable.userservice.entity;

public class CartItem {
	
	private String productName;
	private Integer quantity;
	
	// No-argument constructor
    public CartItem() {
    }

    // Constructor with all fields
    public CartItem(String productName, Integer quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    // Getter for productName
    public String getProductName() {
        return productName;
    }

    // Setter for productName
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getter for quantity
    public Integer getQuantity() {
        return quantity;
    }

    // Setter for quantity
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
