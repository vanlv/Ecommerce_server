package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.user.User;
import com.example.demo.repository.UserRepository;

@Service
public class RecommendService {

	@Autowired
	private EntityManager manager;

	@Autowired
	private UserRepository userRepos;

	public List<Long> getAllProductIdUserLikeAndRating(String username) {

//		select l.product_id, u.fullname
//		from tbl_product_like l
//		inner join tbl_user u on l.user_id = u.id
//		and u.id = 32
//		union
//		select c.product_id, u.fullname
//		from tbl_comment c
//		inner join tbl_user u on c.user_id = u.id
//		and c.rating >= 3
//		and u.id = 32

		// select l.product_id
//		from tbl_product_like l
//		inner join tbl_user u on l.user_id = u.id
//		and u.id = 32

		User user = userRepos.findOneByUsername(username);
		String sql1 = "select l.product.id from LikedProduct l inner join User u on u.id = l.user.id and u.id = "
				+ user.getId();
		String sql2 = "select c.product.id from Comment c inner join User u on u.id = c.user.id and c.rating >= 3 and u.id = "
				+ user.getId();

		Query q1 = manager.createQuery(sql1);
		Query q2 = manager.createQuery(sql2);

		@SuppressWarnings("unchecked")
		List<Long> list1 = q1.getResultList();
		@SuppressWarnings("unchecked")
		List<Long> list2 = q2.getResultList();
		
		List<Long> result = new ArrayList<Long>(list1);
		result.addAll(list2);
		result = result.stream().distinct().collect(Collectors.toList());
		return result;

	}

}
