package com.piseth.java.school.phoneshopenight.service;

import com.piseth.java.school.phoneshopenight.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer id);
	Brand update(Integer id, Brand brandUpdate);
}
