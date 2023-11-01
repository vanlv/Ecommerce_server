package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Brand;
import com.example.demo.entity.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	public Page<Product> findAllByBrand(Brand brand, Pageable pageable);
	
}
