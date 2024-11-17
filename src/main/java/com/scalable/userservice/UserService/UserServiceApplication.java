package com.scalable.userservice.UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
		exclude = { SecurityAutoConfiguration.class },
		scanBasePackages = { "com.scalable.userservice.controller", "com.scalable.userservice.service"}
)
@EntityScan(basePackages = "com.scalable.userservice.entity")
@EnableJpaRepositories(basePackages = "com.scalable.userservice.repository")
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}