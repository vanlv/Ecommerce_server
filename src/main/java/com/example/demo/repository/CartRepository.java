package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.order.Cart;
import com.example.demo.entity.user.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	public Cart getOneByUser(User user);
	
	public Boolean existsByUser(User user);
	
}
