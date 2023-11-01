package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.common.Erole;
import com.example.demo.entity.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findOneByName(Erole name);
	
	public Boolean existsByName(Erole name);
	
}
