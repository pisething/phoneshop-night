package com.piseth.java.school.phoneshopenight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.piseth.java.school.phoneshopenight.dto.ModelDTO;
import com.piseth.java.school.phoneshopenight.entity.Model;
import com.piseth.java.school.phoneshopenight.service.BrandService;

@Mapper( componentModel = "spring", uses = {BrandService.class})
public interface ModelEntityMapper {
	ModelEntityMapper INSTANCE = Mappers.getMapper(ModelEntityMapper.class);
	
	@Mapping(target = "brand", source = "brandId")
	Model toModel(ModelDTO dto);
	
	@Mapping(target = "brandId", source = "brand.id")
	ModelDTO toModelDTO(Model model);
	
	/*
	default Brand toBrand(Integer brId) {
		Brand brand = new Brand();
		brand.setId(brId);
		return brand;
	}
	*/
}
