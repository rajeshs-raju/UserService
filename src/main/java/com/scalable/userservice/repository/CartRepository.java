package com.scalable.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.scalable.userservice.entity.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, Long>{
	Cart findByUserName(String userName);
}
