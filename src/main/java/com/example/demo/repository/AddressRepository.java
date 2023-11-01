package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.user.Address;
import com.example.demo.entity.user.User;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	public List<Address> findAllByUser(User user);
	
}
