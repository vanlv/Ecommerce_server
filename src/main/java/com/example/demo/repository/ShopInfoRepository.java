package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.user.ShopInfo;

@Repository
public interface ShopInfoRepository extends JpaRepository<ShopInfo, Long>{

	@Query("select entity from ShopInfo as entity")
	public ShopInfo getShopInfo();
	
}
