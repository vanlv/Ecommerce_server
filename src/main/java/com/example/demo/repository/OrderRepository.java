package com.example.demo.repository;

import com.example.demo.entity.order.Order;
import com.example.demo.entity.user.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	public List<Order> getAllByUser(User user, Sort sort);
	
	public Page<Order> getAllBySeller(User user, Pageable pageable);
	
	public Boolean existsByStatus(Long status);
	
	@Query("SELECT sum(e.total_price) from Order e where e.status = 2 AND (TIMESTAMPDIFF(DAY, e.createdDate, NOW())<=?1)")
	public Long totalRevenueFromOrder(Integer lastDate);
	
	@Query("SELECT COUNT(entity) FROM Order entity WHERE entity.status=?1")
	public Integer countOrderByStatus(Integer status);
	
	@Query("SELECT COUNT(entity) FROM Order entity WHERE entity.status=?1 AND entity.user.id =?2")
	public Integer countOrderByStatusAndUser(Integer status, Long user_id);
	
	@Query("SELECT COUNT(entity) FROM Order entity WHERE entity.status=?1 AND entity.seller.id =?2")
	public Integer countOrderByStatusAndSeller(Integer status, Long seller_id);
}
