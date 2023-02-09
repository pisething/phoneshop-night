package com.piseth.java.school.phoneshopenight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phoneshopenight.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long>
{
	
}
