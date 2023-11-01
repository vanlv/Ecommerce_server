package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Product;
import com.example.demo.entity.product.Tivi;

@Repository
public interface TiviRepository extends JpaRepository<Tivi, Long> {

	public Tivi findOneByProduct(Product product);
	
}
