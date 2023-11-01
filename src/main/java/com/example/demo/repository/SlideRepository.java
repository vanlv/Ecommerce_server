package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.other.Slide;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {

	@Query("select entity from Slide entity where entity.display = 1")
	public List<Slide> getList();

}
