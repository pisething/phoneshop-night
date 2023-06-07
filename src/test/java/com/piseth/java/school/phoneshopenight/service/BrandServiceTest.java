package com.piseth.java.school.phoneshopenight.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.piseth.java.school.phoneshopenight.entity.Brand;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.repository.BrandRepository;
import com.piseth.java.school.phoneshopenight.service.impl.BrandServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

	@Mock
	private BrandRepository brandRepository;
	
	@Captor
	private ArgumentCaptor<Brand> brandCaptor;

	private BrandService brandService;

	@BeforeEach
	public void setUp() {
		brandService = new BrandServiceImpl(brandRepository);
	}
/*
	@Test
	public void testCreate() {
		// given
		Brand brand = new Brand();
		brand.setName("Apple");
		brand.setId(1);
		
		Brand brand2 = new Brand();
		brand.setName("Apple");

		// when
		when(brandRepository.save(any(Brand.class))).thenReturn(brand);
		//when(brandRepository.save(brand2)).thenReturn(brand);
		Brand brandReturn = brandService.create(new Brand());
		// then
		assertEquals(1, brandReturn.getId());
		assertEquals("Apple", brandReturn.getName());

	}
	*/
	
	@Test
	public void testCreate() {
		// given
		Brand brand = new Brand();
		brand.setName("Apple");
		// when
		brandService.create(brand);
		// then
		verify(brandRepository, times(1)).save(brand);
		//verify(brandRepository, times(1)).delete(brand);
	}
	
	@Test
	public void testGetByIdSuccess() {
		//given
		Brand brand = new Brand();
		brand.setName("Apple");
		brand.setId(1L);
		
		//when
		when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
		Brand brandReturn = brandService.getById(1L);
		//then
		
		assertEquals(1, brandReturn.getId());
		assertEquals("Apple", brandReturn.getName());
		
	}
	
	@Test
	public void testGetByIdThrow() {
		//given
		
		//when
		when(brandRepository.findById(2L)).thenReturn(Optional.empty());
		//brandService.getById(2);
		assertThatThrownBy(() -> brandService.getById(2L))
			.isInstanceOf(ResourceNotFoundException.class)
			.hasMessage("Brand With id = 2 not found");
			//.hasMessage(String.format("%s With id = %d not found","Brand",2 ));
			//.hasMessageEndingWith("not found");
		//then
	}
	
	@Test
	public void testUpdate() {
		// given
		Brand brandInDB = new Brand(1L, "Apple");
		Brand brand = new Brand(1L, "Apple 2");
		// brand(1,"Apple")
		//when
		when(brandRepository.findById(1L)).thenReturn(Optional.ofNullable(brandInDB));
		//when(brandRepository.save(any(Brand.class))).thenReturn(brand);
		Brand brandAfterUpdate = brandService.update(1L, brand);
		
		//then
		verify(brandRepository, times(1)).findById(1L);
		//assertEquals("Apple 2U", brandAfterUpdate.getName());
		verify(brandRepository).save(brandCaptor.capture());
		assertEquals("Piseth", brandCaptor.getValue().getName());
		assertEquals(1L, brandCaptor.getValue().getId());
	}
	
	
}
