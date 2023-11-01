package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.order.Cart;
import com.example.demo.entity.order.CartDetail;
import com.example.demo.entity.product.Product;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

	public CartDetail getByProductAndCart(Product product, Cart cart);
	
	public List<CartDetail> findAllByCart(Cart cart);
	
	public Boolean existsByProductAndCart(Product product, Cart cart);
	
	@Transactional
	@Modifying
	@Query("Delete from CartDetail entity WHERE entity.id =?1")
	public void deleteByCartDetailId(Long id);
	
	@Transactional
	@Modifying
	@Query("Delete from CartDetail entity WHERE entity.cart.id =?1")
	public void deleteByCartId(Long id);
	
}
