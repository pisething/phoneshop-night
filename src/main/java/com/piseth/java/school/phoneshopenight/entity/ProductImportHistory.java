package com.piseth.java.school.phoneshopenight.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "productImportHistories")
public class ProductImportHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "import_id")
	private Long id;
	
	@Column(name = "date_import")
	private LocalDate dateImport;
	
	@Column(name = "import_unit")
	private Integer importUnit;
	
	@Column(name = "price_per_unit")
	private BigDecimal pricePerUnit;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
}
