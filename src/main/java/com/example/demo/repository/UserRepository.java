package com.example.demo.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.user.Role;
import com.example.demo.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select entity from User entity where entity.email = ?1 OR entity.username = ?1 and entity.display=1")
	Optional<User> findByUsernameOrEmail(String username);

	List<User> findByRolesIn(Collection<Role> names, Pageable pageable);
	
	public User findOneByUsername(String username);

	public boolean existsByUsername(String username);

	public boolean existsByEmail(String email);
	
	public boolean existsByPhone(String phone);
	
	public User findOneByEmail(String email);
	
	@Query("select entity from User entity where entity.display = 1")
	public Page<User> getList(Pageable pageable);

}
