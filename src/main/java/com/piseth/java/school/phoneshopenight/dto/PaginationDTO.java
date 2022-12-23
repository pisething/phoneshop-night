package com.piseth.java.school.phoneshopenight.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationDTO {
	private int pageSize;
	private int pageNumber;
	private int totalPages;
	private long totalElements;
	private long numberOfElements;
	private boolean first;
	private boolean last;
	private boolean empty;

}
