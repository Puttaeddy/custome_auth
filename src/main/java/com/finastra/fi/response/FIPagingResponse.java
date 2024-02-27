package com.finastra.fi.response;

import java.util.List;

import com.finastra.fi.dao.dto.FIDetailsDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FIPagingResponse {

	private List<FIDetailsDTO> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;	
	private boolean lastPage;
}
