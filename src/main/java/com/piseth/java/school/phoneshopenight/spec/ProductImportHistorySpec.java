package com.piseth.java.school.phoneshopenight.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.piseth.java.school.phoneshopenight.entity.ProductImportHistory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductImportHistorySpec implements Specification<ProductImportHistory>{
	private ProductImportHistoryFilter importFilter;
	
	@Override
	public Predicate toPredicate(Root<ProductImportHistory> importHistory, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		if(Objects.nonNull(importFilter.getStartDate())) { 
			cb.greaterThanOrEqualTo(importHistory.get("dateImport"), importFilter.getStartDate());
		}
		if(Objects.nonNull(importFilter.getEndDate())) { 
			cb.lessThanOrEqualTo(importHistory.get("dateImport"), importFilter.getEndDate());
		}
		Predicate predicate = cb.and(predicates.toArray(Predicate[]::new));
		return predicate;
	}

}
