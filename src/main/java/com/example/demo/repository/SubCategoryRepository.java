package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.category.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
	public SubCategory findOneByCode(String code);

	public List<SubCategory> findAllByCategoryCode(String code);
	
	@Query("select count(entity.id) from SubCategory entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, Long id);
	
	@Query("select entity from SubCategory entity where entity.display = 1")
	public Page<SubCategory> getList(Pageable pageable);
	
	@Query("select entity from SubCategory entity where entity.display = 0")
	public Page<SubCategory> getListHide(Pageable pageable);
}
