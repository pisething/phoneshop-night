package com.piseth.java.school.phoneshopenight.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.dto.ProductSoldDTO;
import com.piseth.java.school.phoneshopenight.dto.SaleDTO;
import com.piseth.java.school.phoneshopenight.dto.SaleResponeDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.Sale;
import com.piseth.java.school.phoneshopenight.entity.SaleDetail;
import com.piseth.java.school.phoneshopenight.exception.ApiException;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleDetailRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleRepository;
import com.piseth.java.school.phoneshopenight.service.ProductService;
import com.piseth.java.school.phoneshopenight.service.SaleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService{
	private final ProductService productService;
	private final ProductRepository productRepository;
	private final SaleRepository saleRepository;
	private final SaleDetailRepository saleDetailRepository;

	@Override
	public SaleResponeDTO sell(SaleDTO saleDTO) {
		
		List<Long> productIds = saleDTO.getProducts().stream()
				.map(ProductSoldDTO::getProductId)
				.toList();
			// validate product
		productIds.forEach(productService::getById);
		
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		
		// validate stock
		saleDTO.getProducts()
			.forEach(ps ->{
				Product product = productMap.get(ps.getProductId());
				if(product.getAvailableUnit() < ps.getNumberOfUnit()) {
					throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough in stock".formatted(product.getName()));
				}
			});
		
		// Sale
		Sale sale = new Sale();
		sale.setSoldDate(saleDTO.getSaleDate());
		saleRepository.save(sale);
		
		// Sale Detail
		saleDTO.getProducts().forEach(ps ->{
			Product product = productMap.get(ps.getProductId());
			SaleDetail saleDetail = new SaleDetail();
			saleDetail.setAmount(product.getSalePrice());
			saleDetail.setProduct(product);
			saleDetail.setSale(sale);
			saleDetail.setUnit(ps.getNumberOfUnit());
			saleDetailRepository.save(saleDetail);
			
			// cut stock
			Integer availableUnit =  product.getAvailableUnit() - ps.getNumberOfUnit();
			product.setAvailableUnit(availableUnit);
			productRepository.save(product);
		});
		
		SaleResponeDTO responeDTO = new SaleResponeDTO();
		responeDTO.setSaleId(sale.getId());
		return responeDTO;
	}
	
	
	
	
	
	private void saveSale(SaleDTO saleDTO) {
		Sale sale = new Sale();
		sale.setSoldDate(saleDTO.getSaleDate());
		saleRepository.save(sale);
		
		
		//Sale Detail
		saleDTO.getProducts().forEach(ps ->{
			SaleDetail saleDetail = new SaleDetail();
			saleDetail.setAmount(null);
		});
	}
	
	private void validate(SaleDTO saleDTO) {
		saleDTO.getProducts().forEach(ps ->{
			Product product = productService.getById(ps.getProductId());
			if(product.getAvailableUnit() < ps.getNumberOfUnit()) {
				throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough in stock".formatted(product.getName()));
			}
		});
	}
	
	
	
	private void validate2(SaleDTO saleDTO) {
		
		
		List<Long> productIds = saleDTO.getProducts().stream()
			.map(ProductSoldDTO::getProductId)
			.toList();
		// validate product
		productIds.forEach(productService::getById);
		
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		
		// validate stock
		saleDTO.getProducts()
			.forEach(ps ->{
				Product product = productMap.get(ps.getProductId());
				if(product.getAvailableUnit() < ps.getNumberOfUnit()) {
					throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough in stock".formatted(product.getName()));
				}
			});
		
	}





	@Override
	public void cancelSale(Long saleId) {
		// update sale status
		Sale sale = getById(saleId);
		sale.setActive(false);
		saleRepository.save(sale);
		
		// update stock
		List<SaleDetail> saleDetails = saleDetailRepository.findBySaleId(saleId);
		
		List<Long> productIds = saleDetails.stream()
			.map(sd -> sd.getProduct().getId())
			.toList();
		
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		saleDetails.forEach(sd ->{
			 Product product = productMap.get(sd.getProduct().getId());
			 product.setAvailableUnit(product.getAvailableUnit() + sd.getUnit());
			 productRepository.save(product);
		});
		
		
	}





	@Override
	public Sale getById(Long saleId) {
		return saleRepository.findById(saleId)
					.orElseThrow(() -> new ResourceNotFoundException("Sale", saleId));
	}

}
