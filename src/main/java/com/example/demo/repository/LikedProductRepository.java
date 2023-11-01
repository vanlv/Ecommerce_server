package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Product;
import com.example.demo.entity.user.LikedProduct;
import com.example.demo.entity.user.User;

@Repository
public interface LikedProductRepository extends JpaRepository<LikedProduct, Long> {

	public List<LikedProduct> getAllByUser(User user, Sort sort);

	public Boolean deleteByUserAndProduct(User user, Product product);

	public LikedProduct getOneByUserAndProduct(User user, Product product);
}
