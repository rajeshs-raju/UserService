package com.scalable.userservice.controller;

import com.scalable.userservice.entity.User;
import com.scalable.userservice.service.UserService;
import com.scalable.userservice.utils.JwtUtil;
import io.jsonwebtoken.Claims;
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

        // Generate JWT token for the user
        String token = JwtUtil.generateToken(createdUser.getUsername());

        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User created successfully");
        response.put("userId", createdUser.getId());
        response.put("username", createdUser.getUsername());
        response.put("email", createdUser.getEmail());
        response.put("token", token); // Include the token

        // Return response with status 201 Created
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Authenticate the user
        User user = userService.authenticateUser(username, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password"));
        }

        // Generate JWT token for the user
        String token = JwtUtil.generateToken(user.getUsername());

        // Prepare the response with token
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("token", token);

        return ResponseEntity.ok(response);  // Return 200 with token
    }


    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@RequestHeader("Authorization") String token ,@PathVariable Long id) {
        String jwt = token.replace("Bearer ", "");
        Claims claims = JwtUtil.validateToken(jwt);
        String username = claims.getSubject();
        System.out.println("Request made by user: " + username);
        User user = userService.findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();  // Return 404 if user not found
        }
        return ResponseEntity.ok(user);  // Return 200 with user data
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", "");
        Claims claims = JwtUtil.validateToken(jwt);
        String username = claims.getSubject();
        System.out.println("Request made by user: " + username);
        List<User> users = userService.getListOfUsers();
        return ResponseEntity.ok(users);
    }

}