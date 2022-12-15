package com.piseth.java.school.phoneshopenight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.piseth.java.school.phoneshopenight.dto.BrandDTO;
import com.piseth.java.school.phoneshopenight.entity.Brand;

@Mapper
public interface BrandMapper {
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
	
	Brand toBrand(BrandDTO dto);
	
	BrandDTO toBrandDTO(Brand entity);
}

