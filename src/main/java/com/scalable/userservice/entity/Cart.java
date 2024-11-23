package com.scalable.userservice.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Cart")
public class Cart {
    
	@Id
    private String userName;
    
    private Double totalCost;
    
    private List<CartItem> cartItems;
    
 // No-argument constructor
    public Cart() {
    }

    


    public Cart(String userName, Double totalCost, List<CartItem> cartItems) {
	this.userName = userName;
	this.totalCost = totalCost;
	this.cartItems = cartItems;
}




	// Getter for totalCost
    public Double getTotalCost() {
        return totalCost;
    }

    // Setter for totalCost
    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    // Getter for cartItems
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    // Setter for cartItems
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    
}
