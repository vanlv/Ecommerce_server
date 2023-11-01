//package com.example.demo.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.example.demo.entity.product.Tag;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//@Repository
//public interface TagRepository extends JpaRepository<Tag, Long> {
//
//	public Tag getOneByCode(String code);
//	
//	@Query("select count(entity.id) from Tag entity where entity.name =?1 and (entity.id <> ?2 or ?2 is null) ")
//	Long checkCode(String name, Long id);
//	
//	@Query("select entity from Tag entity")
//	public Page<Tag> getList(Pageable pageable);
//
//}
