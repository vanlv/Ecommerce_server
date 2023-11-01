package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Product;
import com.example.demo.entity.user.Comment;
import com.example.demo.entity.user.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	public List<Comment> findAllByUser(User user);
	
	public List<Comment> findAllByProduct(Product product);
	
	public Integer countAllByProduct(Product product);
	
	public Boolean existsByProductAndUser(Product product, User user);
	
}
