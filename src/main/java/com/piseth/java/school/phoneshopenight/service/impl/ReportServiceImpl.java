package com.piseth.java.school.phoneshopenight.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.dto.ProductReportDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.SaleDetail;
import com.piseth.java.school.phoneshopenight.projection.ProductSold;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleDetailRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleRepository;
import com.piseth.java.school.phoneshopenight.service.ReportService;
import com.piseth.java.school.phoneshopenight.spec.SaleDetailFilter;
import com.piseth.java.school.phoneshopenight.spec.SaleDetailSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	
	private final SaleRepository saleRepository;
	private final SaleDetailRepository saleDetailRepository;
	private final ProductRepository productRepository;

	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		return saleRepository.findProductSold(startDate, endDate);
	}

	@Override
	public List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate) {
		List<ProductReportDTO> list = new ArrayList<>();
		
		SaleDetailFilter detailFilter = new SaleDetailFilter();
		detailFilter.setStartDate(startDate);
		detailFilter.setEndDate(endDate);
		Specification<SaleDetail> spec = new SaleDetailSpec(detailFilter);
		List<SaleDetail> saleDetails = saleDetailRepository.findAll(spec);
		
		List<Long> productIds = saleDetails.stream()
			.map(sd -> sd.getProduct().getId())
			.toList();
		Map<Long, Product> productMap = productRepository.findAllById(productIds).stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		Map<Product, List<SaleDetail>> saleDetailMap = saleDetails.stream()
			.collect(Collectors.groupingBy(SaleDetail::getProduct));
		
		for(var entry: saleDetailMap.entrySet()) {
			Product product = productMap.get(entry.getKey().getId());
			List<SaleDetail> sdList = entry.getValue();
			
			//total unit
			Integer unit = sdList.stream().map(SaleDetail::getUnit)
				.reduce(0, (a,b) -> a+b);
			/*
			 Integer integer = sdList.stream().map(SaleDetail::getUnit)
					.reduce((a,b) -> a+b)
					.get();
			*/
			/*
			Double totalAmount = sdList.stream()
				.map(sd -> sd.getUnit() * sd.getAmount().doubleValue())
				.reduce(0d, (a,b) -> a+b);
			*/
			double totalAmount = sdList.stream()
				.mapToDouble(sd -> sd.getUnit() * sd.getAmount().doubleValue())
				.sum();
				
			//.map(sd -> sd.getUnit() * sd.getAmount().doubleValue())
			
			ProductReportDTO reportDTO = new ProductReportDTO();
			reportDTO.setProductId(product.getId());
			reportDTO.setProductName(product.getName());
			reportDTO.setUnit(unit);
			reportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
			list.add(reportDTO);
		}
		
		return list;
	}

}
