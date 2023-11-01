package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.order.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	public boolean existsByTradingCode(String tradingCode);
	
	public Payment findOneByOrderId(Long id);
	
	@Query("select count(entity.id) from Payment entity where entity.tradingCode =?1")
	Long checkCode(String tradingCode);
}
