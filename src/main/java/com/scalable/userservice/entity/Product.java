package com.scalable.userservice.entity;

import org.springframework.data.annotation.Id;

public class Product {
	
    private String productName;
    private Integer quantity;
    private Double amount;
 
    
	public Product() {
	}
	public Product(String productName, Integer quantity, Double amount) {
		this.productName = productName;
		this.quantity = quantity;
		this.amount = amount;
	}


	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
