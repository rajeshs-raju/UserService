package com.scalable.userservice.entity;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "Order")
public class Order {

    @Id
    private String userName;
    
    private double amount;
    
    private Cart cart;
    
    
    
	public Order() {
	}


	public Order(String userName, double amount, Cart cart) {
		this.userName = userName;
		this.amount = amount;
		this.cart = cart;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}
	  

}
