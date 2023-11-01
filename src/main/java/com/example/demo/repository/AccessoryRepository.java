package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Accessory;
import com.example.demo.entity.product.Product;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {

	public Accessory findOneByProduct(Product product);
	
}
