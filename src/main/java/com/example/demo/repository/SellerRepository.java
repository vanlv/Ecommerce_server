package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.user.Seller;
import com.example.demo.entity.user.User;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

	public Seller findOneByUser(User user);

}
