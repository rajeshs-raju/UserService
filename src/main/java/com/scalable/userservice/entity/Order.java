package com.scalable.userservice.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;
    private String status;
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    // Getters and Setters
}

