package com.piseth.java.school.phoneshopenight.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piseth.java.school.phoneshopenight.entity.Model;

public interface ModelRepository extends JpaRepository<Model, Long>{
	List<Model> findByBrandId(Long brandId);
	
}
