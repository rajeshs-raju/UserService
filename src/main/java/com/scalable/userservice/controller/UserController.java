package com.scalable.userservice.controller;

import com.scalable.userservice.entity.User;
import com.scalable.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userservice")
public class UserController {
    @Autowired
    private UserService userService;

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


    // API to get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();  // Return 404 if user not found
        }
        return ResponseEntity.ok(user);  // Return 200 with user data
    }

    // API to get the list of all users
    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getListOfUsers();
        return ResponseEntity.ok(users);  // Return list of users
    }
}