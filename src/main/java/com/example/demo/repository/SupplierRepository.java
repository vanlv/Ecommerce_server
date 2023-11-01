package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.inventory.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	public Supplier findOneByCode(String code);

	@Query("select count(entity.id) from Supplier entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, Long id);

	@Query("select entity from Supplier entity where entity.display = 1")
	public Page<Supplier> getList(Pageable pageable);
}
