package com.scalable.userservice.service;

import com.scalable.userservice.entity.User;
import com.scalable.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Method to register a user (saving to the database)
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Method to find a user by ID
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Method to get a list of all users
    public List<User> getListOfUsers() {
        return userRepository.findAll();  // Fetch all users
    }
}