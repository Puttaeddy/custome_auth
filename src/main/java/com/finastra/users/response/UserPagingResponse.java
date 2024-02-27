package com.finastra.users.response;

import com.finastra.users.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserPagingResponse {

	
	//private List<UserDto> content;
	private List<UserResponse> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;	
	private boolean lastPage;
	
	
}
