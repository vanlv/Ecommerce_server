package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.product.Product;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	public List<OrderDetail> getAllByProductId(Long id);
	
	public List<OrderDetail> getAllByOrderId(Long id);
	
	public List<OrderDetail> getByOrderAndProduct(Order order, Product product);
	
	@Query("SELECT sum(u.quantity) FROM OrderDetail u WHERE u.product.id=?1 AND u.order.status=2")
	public Integer countAllByProductId(Long id);
	
	// tính tổng 5 sản phẩm bản chạy nhất
//	SELECT product_id, sum(amount) As MostSold 
//	FROM tbl_order_detail, tbl_order
//	where tbl_order.status=2
//	AND tbl_order_detail.order_id = tbl_order.id
//	Group By product_id
//	ORDER BY MostSold DESC limit 5
	
}
