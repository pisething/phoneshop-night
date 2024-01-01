package com.piseth.java.school.phoneshopenight.service;

import com.piseth.java.school.phoneshopenight.dto.SaleDTO;
import com.piseth.java.school.phoneshopenight.dto.SaleResponeDTO;
import com.piseth.java.school.phoneshopenight.entity.Sale;

public interface SaleService {
	SaleResponeDTO sell(SaleDTO saleDTO);
	Sale getById(Long saleId);
	void cancelSale(Long saleId);
}
