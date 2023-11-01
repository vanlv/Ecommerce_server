package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Product;
import com.example.demo.entity.product.Technology;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
	public Technology findOneByProduct(Product product);
}
