package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.order.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

	public Shipment findOneByOrderId(Long id);

	@Query("select count(entity.id) from Shipment entity where entity.order_code =?1")
	Long checkCode(String order_code);
}
