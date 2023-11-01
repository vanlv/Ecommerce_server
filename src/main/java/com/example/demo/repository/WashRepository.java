package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Product;
import com.example.demo.entity.product.Wash;

@Repository
public interface WashRepository extends JpaRepository<Wash, Long> {

	public Wash findOneByProduct(Product product);
	
}
