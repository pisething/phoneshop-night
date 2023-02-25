package com.piseth.java.school.phoneshopenight.service;

import java.math.BigDecimal;

import com.piseth.java.school.phoneshopenight.dto.ProductImportDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;

public interface ProductService {

	Product create(Product product);
	
	Product getById(Long id);
	
	void importProduct(ProductImportDTO importDTO);
	
	void setSalePrice(Long productId, BigDecimal price);
	
	void validateStock(Long productId, Integer numberOfUnit);
}
