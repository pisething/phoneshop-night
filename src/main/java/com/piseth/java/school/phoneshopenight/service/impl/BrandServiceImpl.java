package com.piseth.java.school.phoneshopenight.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.entity.Brand;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.repository.BrandRepository;
import com.piseth.java.school.phoneshopenight.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Brand create(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public Brand getById(Integer id) {
		return brandRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Brand", id));
	}

	@Override
	public Brand update(Integer id, Brand brandUpdate) {
		Brand brand = getById(id);
		brand.setName(brandUpdate.getName()); // @TODO improve update
		return brandRepository.save(brand);
	}

}
