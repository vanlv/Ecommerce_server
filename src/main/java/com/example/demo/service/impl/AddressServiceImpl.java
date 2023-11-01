package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.user.UserAddressDto;
import com.example.demo.entity.user.Address;
import com.example.demo.entity.user.User;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addRepos;
	
//	@Autowired
//	private ProvinceRepository provinceRepos;
//	
//	@Autowired
//	private DistrictRepository districtRepos;
//	
//	@Autowired
//	private WardRepository wardRepos;

	@Autowired
	private UserRepository userRepos;

	@Override
	public UserAddressDto saveOrUpdate(UserAddressDto dto) {
		if (dto != null) {
			Address entity = null;

			User user = userRepos.findOneByUsername(dto.getUsername());

			if (dto.getId() != null) {
				entity = addRepos.getById(dto.getId());
			}
			if (entity == null) {
				entity = new Address();
			}

			user.setPhone(dto.getPhone());
//			entity.setCity(provinceRepos.findOneByProvinceid(dto.getCity()).getName());
//			entity.setDistrict(districtRepos.findOneByDistrictid(dto.getDistrict()).getName());
//			entity.setWard(wardRepos.findOneByWardid(dto.getWard()).getName());
			entity.setCity(dto.getCity());
			entity.setCity_id(dto.getCity_id());
			entity.setDistrict(dto.getDistrict());
			entity.setDistrict_id(dto.getDistrict_id());
			entity.setWard(dto.getWard());
			entity.setWard_id(dto.getWard_id());
			entity.setHouse(dto.getHouse());
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			entity.setUser(user);
			
			user.setAddress(entity);

			entity = addRepos.save(entity);
			userRepos.save(user);

			if (entity != null) {
				return new UserAddressDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			addRepos.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public List<UserAddressDto> getAllAddressByUser(String username) {
		List<UserAddressDto> list = new ArrayList<>();
		User user = userRepos.findOneByUsername(username);
		List<Address> entities = addRepos.findAllByUser(user);
		for (Address entity : entities) {
			UserAddressDto dto = new UserAddressDto(entity);
			list.add(dto);
		}
		return list;
	}

}
