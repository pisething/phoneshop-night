package com.piseth.java.school.phoneshopenight.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piseth.java.school.phoneshopenight.entity.SaleDetail;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long>
{
	List<SaleDetail> findBySaleId(Long saleId);
}
