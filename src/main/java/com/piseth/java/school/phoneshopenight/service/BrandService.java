package com.piseth.java.school.phoneshopenight.service;

import java.util.List;

import com.piseth.java.school.phoneshopenight.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer id);
	Brand update(Integer id, Brand brandUpdate);
	List<Brand> getBrands();
	List<Brand> getBrands(String name);
}
