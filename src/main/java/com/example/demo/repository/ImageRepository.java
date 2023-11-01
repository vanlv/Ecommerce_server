package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.product.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	public List<Image> findAllByProductId(Long id);
	
	@Transactional
	@Modifying
	@Query("Delete from Image entity WHERE entity.product.id =?1")
	public void deleteByProductId(Long id);
}
