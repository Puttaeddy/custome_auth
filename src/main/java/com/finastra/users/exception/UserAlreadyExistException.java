package com.finastra.users.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAlreadyExistException extends RuntimeException {

	final String  fieldName;
	public UserAlreadyExistException(String fieldName) {
		super(String.format("%s already exists..",  fieldName));
		this.fieldName = fieldName;

	}
}
