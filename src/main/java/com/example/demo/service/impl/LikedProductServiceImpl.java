package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.user.LikedProductDto;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.user.LikedProduct;
import com.example.demo.entity.user.User;
import com.example.demo.repository.LikedProductRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LikedProductService;

@Service
public class LikedProductServiceImpl implements LikedProductService {

	@Autowired
	private UserRepository userRepos;

	@Autowired
	private ProductRepository productRepos;

	@Autowired
	private LikedProductRepository likedRepos;

	@Override
	public List<LikedProductDto> getListByUser(String username) {
		// TODO Auto-generated method stub
		User user = userRepos.findOneByUsername(username);
		List<LikedProduct> list = likedRepos.getAllByUser(user, Sort.by("createdDate").descending());
		List<LikedProductDto> dtos = new ArrayList<>();
		for (LikedProduct p : list) {
			LikedProductDto dto = new LikedProductDto(p);
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public LikedProductDto saveOrUpdate(LikedProductDto dto) {
		if (dto != null) {
			User user = userRepos.findOneByUsername(dto.getUsername());
			Product product = productRepos.getById(dto.getProductId());

			LikedProduct entity = new LikedProduct();

			entity.setUser(user);
			entity.setProduct(product);
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			entity = likedRepos.save(entity);

			if (entity != null) {
				return new LikedProductDto(entity);
			}
		}
		return null;

	}

	@Override
	public Boolean delete(String username, Long productId) {
		if (username != null && productId != null) {
			User user = userRepos.findOneByUsername(username);
			Product product = productRepos.getById(productId);
			LikedProduct entity = likedRepos.getOneByUserAndProduct(user, product);
			likedRepos.delete(entity);
			return true;
		}
		return false;
	}

	@Override
	public Boolean getByUserAndProduct(String username, Long productId) {
		if (username != null && productId != null) {
			User user = userRepos.findOneByUsername(username);
			Product product = productRepos.getById(productId);
			LikedProduct entity = likedRepos.getOneByUserAndProduct(user, product);
			if(entity != null) {
				return true;
			} else {
				return false;
			}
		}
		return null;
	}

}
