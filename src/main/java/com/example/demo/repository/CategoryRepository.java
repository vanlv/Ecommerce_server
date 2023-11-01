package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.category.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	public Category findOneByCode(String code);
	
	@Query("select count(entity.id) from Category entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, Long id);
	
	@Query("select entity from Category entity where entity.display = 1")
	public Page<Category> getList(Pageable pageable);
	
	@Query("select entity from Category entity where entity.display = 0")
	public Page<Category> getListHide(Pageable pageable);
}
