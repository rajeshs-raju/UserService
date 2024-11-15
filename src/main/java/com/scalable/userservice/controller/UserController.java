package com.scalable.userservice.controller;

import com.scalable.userservice.entity.User;
import com.scalable.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

//    @PostMapping("/register")
//    public User registerUser(@RequestBody User user) {
//        System.out.println("Received user data: " + user.getEmail() + " " +  user.getUsername() + " " + user.getId() + " " + user.getPassword()) ;
//        return userService.registerUser(user);
//    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody User user) {
        // Debug log in server console to verify data received
        System.out.println("Received user data: " + user.getEmail() + " " + user.getUsername() + " " + user.getId() + " " + user.getPassword());

        // Call the service to save the user
        User createdUser = userService.registerUser(user);

        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User created successfully");
        response.put("userId", createdUser.getId());
        response.put("username", createdUser.getUsername());
        response.put("email", createdUser.getEmail());

        // Return response with status 201 Created
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }
}