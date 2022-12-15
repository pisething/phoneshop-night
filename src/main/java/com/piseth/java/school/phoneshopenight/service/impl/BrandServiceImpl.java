package com.piseth.java.school.phoneshopenight.service.impl;

import java.util.List;

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

	@Override
	public List<Brand> getBrands() {
		return brandRepository.findAll();
	}

	@Override
	public List<Brand> getBrands(String name) {
		//return brandRepository.findByNameLike("%"+name + "%");
		return brandRepository.findByNameContaining(name);
	}

}
