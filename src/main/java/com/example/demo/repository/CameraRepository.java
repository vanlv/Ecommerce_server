package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Camera;
import com.example.demo.entity.product.Product;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long>{

	public Camera findOneByProduct(Product product);
	
}
