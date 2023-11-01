package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.product.ProductListDto;
import com.example.demo.dto.user.ViewedProductDto;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.user.User;
import com.example.demo.entity.user.ViewedProduct;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.ViewedProductRepository;
import com.example.demo.service.ViewedProductService;

@Service
public class ViewedProductServiceImpl implements ViewedProductService {

	@Autowired
	private EntityManager manager;

	@Autowired
	private UserRepository userRepos;

	@Autowired
	private ProductRepository productRepos;

	@Autowired
	private ViewedProductRepository viewedRepos;

	@Override
	public List<ProductListDto> getListByUser(String username) {
		// TODO Auto-generated method stub
		User user = userRepos.findOneByUsername(username);
		String sql = "SELECT new com.example.demo.dto.user.ViewedProductDto(v) "
				+ " FROM ViewedProduct as v " + "WHERE v.user.id = " + user.getId()  + " GROUP BY v.product " + " ORDER BY v.createdDate DESC ";
		Query q = manager.createQuery(sql, ViewedProductDto.class);
		q.setMaxResults(12);
		@SuppressWarnings("unchecked")
		List<ViewedProductDto> list = q.getResultList();
		List<ProductListDto> dtos = new ArrayList<>();
		for (ViewedProductDto item : list) {
			Product p = productRepos.getById(item.getProductId());
			ProductListDto dto = new ProductListDto(p);
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public ViewedProductDto saveOrUpdate(ViewedProductDto dto) {
		if (dto != null) {
			User user = userRepos.findOneByUsername(dto.getUsername());
			Product product = productRepos.getById(dto.getProductId());
			ViewedProduct entity = new ViewedProduct();
			entity.setUser(user);
			entity.setProduct(product);
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			entity = viewedRepos.save(entity);
			if (entity != null) {
				return new ViewedProductDto(entity);
			}
		}
		return null;
	}

	@Override
	public List<ProductListDto> getListMostPopular() {
		String sql = "SELECT new com.example.demo.dto.user.ViewedProductDto(v.product.id as productId, count(v) as view_count) "
				+ " FROM ViewedProduct as v " + " GROUP BY v.product " + " ORDER BY view_count DESC ";
		Query q = manager.createQuery(sql, ViewedProductDto.class);
		q.setMaxResults(12);
		@SuppressWarnings("unchecked")
		List<ViewedProductDto> list = q.getResultList();
		List<ProductListDto> dtos = new ArrayList<>();
		for (ViewedProductDto item : list) {
			Product p = productRepos.getById(item.getProductId());
			ProductListDto dto = new ProductListDto(p);
			dtos.add(dto);
		}
		return dtos;
	}
}
