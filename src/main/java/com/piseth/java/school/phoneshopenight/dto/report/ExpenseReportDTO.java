package com.piseth.java.school.phoneshopenight.dto.report;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExpenseReportDTO {
	private Long productId;
	private String productName;
	private Integer totalUnit;
	private BigDecimal totalAmount;
}
