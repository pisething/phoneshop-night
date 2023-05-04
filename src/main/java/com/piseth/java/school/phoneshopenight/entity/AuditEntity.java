package com.piseth.java.school.phoneshopenight.entity;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditEntity {
	@CreatedDate
	private LocalDateTime dateCreate;
	
	@LastModifiedDate
	private LocalDateTime dateUpdate;
	
	@CreatedBy
	private String userCreate;
	
	@LastModifiedBy
	private String userUpdate;

}
