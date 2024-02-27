package com.finastra.users.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GraphClientException extends RuntimeException {

	String message;
	String fieldValue;

	public GraphClientException(String message) {
		super(String.format(" %s ",  message));
		this.message = message;

	}

}
