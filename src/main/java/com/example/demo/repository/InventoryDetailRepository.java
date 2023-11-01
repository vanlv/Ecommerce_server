package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.inventory.InventoryDetail;

@Repository
public interface InventoryDetailRepository extends JpaRepository<InventoryDetail, Long> {

	public List<InventoryDetail> getAllByInventoryId(Long id);
	@Query("SELECT sum(id.original_price * id.import_quantity) from InventoryDetail id where (TIMESTAMPDIFF(DAY, id.createdDate, NOW())<=?1)")
	public Long getTotalPriceImport(Integer lastDate);
	
}
