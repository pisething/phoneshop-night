package com.piseth.java.school.phoneshopenight.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.projection.ProductSold;
import com.piseth.java.school.phoneshopenight.repository.SaleRepository;
import com.piseth.java.school.phoneshopenight.service.ReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	
	private final SaleRepository saleRepository;

	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		return saleRepository.findProductSold(startDate, endDate);
	}

}
