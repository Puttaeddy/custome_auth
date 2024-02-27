package com.finastra.users.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MandatoryFieldException extends RuntimeException {

	final String  fieldName;
	final String  message;

	public MandatoryFieldException( String fieldName, String message) {
		super(String.format("%s field is mandatory : %s",  fieldName, message));

		this.fieldName = fieldName;
		this.message = message;
	}
}
