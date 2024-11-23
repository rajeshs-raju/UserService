package com.scalable.userservice.service;

import com.scalable.userservice.entity.Cart;
import com.scalable.userservice.entity.CartItem;
import com.scalable.userservice.entity.Order;
import com.scalable.userservice.entity.Product;
import com.scalable.userservice.entity.User;
import com.scalable.userservice.repository.CartRepository;
import com.scalable.userservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
	
	@Autowired
	private RestTemplate restTemplate;
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CartRepository cartRepository;


    // Authenticate user by checking username and password
    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username).get(); // Fetch user from DB by username
        if (user != null) {
            return user; // Return user if password matches
        }
        return null; // Return null if credentials are invalid
    }
    // Method to register a user (saving to the database)
    public User registerUser(User user) {
        return userRepository.save(user);
    }
    
    public List<Product> getProducts() {
    	String url = "http://localhost:9050/products/products";  
        return restTemplate.getForObject(url, List.class);
    }
    
    public Boolean addToCart(String productName,String userName) { 
    
	    String url = "http://localhost:9050/products/product/{productName}";  
	    Product product = restTemplate.getForObject(url, Product.class,productName);
	    
	    if(product == null) {
	    	return null;
	    }
	    Cart cart = cartRepository.findByUserName(userName);
	    if(cart==null) {
	    	Cart cartTemp = new Cart();
	    	CartItem cartItem = new CartItem();
	    	cartItem.setProductName(productName);
	    	cartItem.setQuantity(1);
	    	List<CartItem> cartItems = new ArrayList<CartItem>();
	    	cartItems.add(cartItem);
	    	cartTemp.setCartItems(cartItems);
	    	cartTemp.setTotalCost(product.getAmount());
	    	cartTemp.setUserName(userName);
	    	cartRepository.save(cartTemp);
	    	return true;
	    }else if(cart!=null) {
	    	List<CartItem> cartItems = cart.getCartItems();
	    	CartItem cartItem = new CartItem();
	    	cartItem.setProductName(productName);
	    	cartItem.setQuantity(1);
	    	cartItems.add(cartItem);
	    	Double totalCost = cart.getTotalCost();
	    	totalCost+=product.getAmount();
	    	cart.setCartItems(cartItems);
	    	cart.setTotalCost(totalCost);
	    	cartRepository.save(cart);
	    	return true;
	    }
	    return false;
	    
    }
    
    public Cart getCart(String userName) {
    	return cartRepository.findByUserName(userName);
    }
    
    public Order addOrder(String userName) {
    	String url = "http://localhost:9051/api/payments/checkout/{userName}";

        return restTemplate.postForObject(url,null ,Order.class, userName);
    }
    
    public Order getOrder(String userName) {
    	String url = "http://localhost:9051/api/payments/orders/{userName}";

        return restTemplate.getForObject(url, Order.class, userName);
    }
    
    
    
    

}