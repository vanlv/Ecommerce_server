package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
	public Brand findOneByCode(String code);
	
	@Query("select count(entity.id) from Brand entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, Long id);
	
	@Query("select entity from Brand entity where entity.display = 1")
	public Page<Brand> getList(Pageable pageable);
	
	@Query("select entity from Brand entity where entity.display = 0")
	public Page<Brand> getListHide(Pageable pageable);
}
