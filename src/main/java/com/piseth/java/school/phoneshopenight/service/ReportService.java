package com.piseth.java.school.phoneshopenight.service;

import java.time.LocalDate;
import java.util.List;

import com.piseth.java.school.phoneshopenight.dto.ProductReportDTO;
import com.piseth.java.school.phoneshopenight.projection.ProductSold;

public interface ReportService {
	
	List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
	List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate);
}
