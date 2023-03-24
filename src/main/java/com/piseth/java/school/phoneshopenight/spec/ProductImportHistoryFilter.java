package com.piseth.java.school.phoneshopenight.spec;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductImportHistoryFilter {
	private LocalDate startDate;
	private LocalDate endDate;
}
