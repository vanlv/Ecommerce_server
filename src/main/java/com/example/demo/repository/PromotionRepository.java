package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.other.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
	@Query("select entity from Promotion entity where entity.display = 1")
	public List<Promotion> getList();
}
