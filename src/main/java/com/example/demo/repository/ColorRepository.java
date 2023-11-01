package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long>{

	public Color findOneByName(String color);

	@Query("select count(entity.id) from Color entity where entity.name =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, Long id);
	
	@Query("select entity from Color entity")
	public Page<Color> getList(Pageable pageable);
	
}
