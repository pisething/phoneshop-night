package com.piseth.java.school.phoneshopenight.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.piseth.java.school.phoneshopenight.dto.ProductReportDTO;
import com.piseth.java.school.phoneshopenight.dto.report.ExpenseReportDTO;
import com.piseth.java.school.phoneshopenight.projection.ProductSold;
import com.piseth.java.school.phoneshopenight.service.ReportService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("reports")
public class ReportController {

	private final ReportService reportService;

	@GetMapping("{startDate}/{endDate}")
	public ResponseEntity<?> productSold(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate, 
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate) {
		List<ProductSold> productSolds = reportService.getProductSold(startDate, endDate);
		return ResponseEntity.ok(productSolds);
	}
	
	@GetMapping("v2/{startDate}/{endDate}")
	public ResponseEntity<?> productSoldV2(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate, 
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate) {
		List<ProductReportDTO> productSolds = reportService.getProductReport(startDate, endDate);
		return ResponseEntity.ok(productSolds);
	}
	
	@GetMapping("expense/{startDate}/{endDate}")
	public ResponseEntity<?> expenseReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate, 
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate) {
		List<ExpenseReportDTO> expenseReportDTOs = reportService.getExpenseReport(startDate, endDate);
		return ResponseEntity.ok(expenseReportDTOs);
	}
	
	

}
