package com.piseth.java.school.phoneshopenight.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.entity.Model;
import com.piseth.java.school.phoneshopenight.repository.ModelRepository;
import com.piseth.java.school.phoneshopenight.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {
	private final ModelRepository modelRepository;

	@Override
	public Model save(Model model) {
		return modelRepository.save(model);
	}

	@Override
	public List<Model> getByBrand(Integer brandId) {
		return modelRepository.findByBrandId(brandId);
	}

}
