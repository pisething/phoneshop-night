package com.piseth.java.school.phoneshopenight.service;

import java.time.LocalDate;
import java.util.List;

import com.piseth.java.school.phoneshopenight.projection.ProductSold;

public interface ReportService {
	
	List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
}
