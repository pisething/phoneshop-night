package com.piseth.java.school.phoneshopenight.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sales")
public class Sale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_id")
	private Long id;
	
	@Column(name = "sold_date")
	private LocalDate soldDate;	
	
	private Boolean active;
}
