package com.scalable.userservice.controller;

import com.scalable.userservice.entity.Cart;
import com.scalable.userservice.entity.Order;
import com.scalable.userservice.entity.Product;
import com.scalable.userservice.entity.User;
import com.scalable.userservice.service.UserService;
import com.scalable.userservice.utils.JwtUtil;

import org.json.HTTP;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userservice")
public class UserController {
	
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        User createdUser = userService.registerUser(user);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Authenticate the user
        User user = userService.authenticateUser(username, password);
        if (user == null) {
            return new ResponseEntity<>(user,HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(user,HttpStatus.OK); 
    }
    
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
    	
    	return new ResponseEntity<>(userService.getProducts(),HttpStatus.OK);
        
    }
    
    @PostMapping("/cart/{userName}/{productName}")
    public ResponseEntity<String> addCart(@PathVariable String productName,@PathVariable String userName) {
    	String message = "";
    	if(userService.addToCart(productName,userName)) {
    		message= "Added to cart";
    	}else {
    		message= "Cart not added";
    	}
    	return new ResponseEntity<>(message,HttpStatus.OK);
        
    }
    
    
    @GetMapping("/cart/{userName}")
    public ResponseEntity<Cart> getCart(@PathVariable String userName) {
    	Cart cart =	userService.getCart(userName);
    	if(cart==null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<>(cart,HttpStatus.OK);
    }
    
    @PostMapping("/cart/checkout/{userName}")
    public ResponseEntity<Order> checkout(@PathVariable String userName) {
    	
    	return new ResponseEntity<>(userService.addOrder(userName),HttpStatus.OK);
        

    }
    
    @GetMapping("/order/{userName}")
    public ResponseEntity<Order> getOrder(@PathVariable String userName) {
    	
    	return new ResponseEntity<>(userService.getOrder(userName),HttpStatus.OK);
        

    }
    
}
    
    