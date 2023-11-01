package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.common.Erole;
import com.example.demo.dto.SearchDto;
import com.example.demo.dto.user.UserDto;
import com.example.demo.entity.user.Role;
import com.example.demo.entity.user.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private EntityManager manager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepos;

	@Override
	public UserDto getCurrentUser(Long id) {
		User user = userRepository.getById(id);
		UserDto dto = new UserDto(user);
		return dto;
	}

	@Override
	public Page<UserDto> getList(SearchDto dto) {
		
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity.id DESC";
		String sqlCount = "select count(entity.id) from User as entity where (1=1) ";
		String sql = "select new com.example.demo.dto.user.UserDto(entity) from User as entity where entity.display=1 AND (1=1)  ";
		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND ( entity.fullname LIKE :text )";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, UserDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<UserDto> entities = q.getResultList();

		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<UserDto> result = new PageImpl<UserDto>(entities, pageable, count);
		return result;
	}

	@Override
	public Page<UserDto> getListByRole(String role, SearchDto dto) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(dto.getPageIndex(), dto.getPageSize());
		Erole erole = Erole.valueOf(role);
		Role roleEntity = roleRepos.findOneByName(erole)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
		List<User> list = userRepository.findByRolesIn(Arrays.asList(roleEntity), pageable);
		List<UserDto> dtos = new ArrayList<>();
		for(User i : list) {
			UserDto u = new UserDto(i);
			if(erole.name().toString().equalsIgnoreCase("ROLE_SELLER")) {
				u.setCccd(i.getSeller().getCccd());
				u.setExp(i.getSeller().getExp());
			}
			dtos.add(u);
		}
		Page<UserDto> listDto = new PageImpl<UserDto>(dtos, pageable, list.size());;
		return listDto;
	}
	
}
