package com.finastra.fi.request;

import java.util.List;

import com.finastra.users.request.AddressRequest;
import com.finastra.users.request.AlternateNumberRequest;
import com.finastra.users.request.OfficeContactNumberRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FIRequest {

	private Long id;

	@NotNull(message = "Oppurtunity ID  cannot be null")
	@NotBlank(message = "Oppurtunity ID cannot be blank")
	@Size(max = 10, message = "Oppurtunity ID cannot exceed 25 characters")
	private String opId;

	@NotNull(message = "FI Name cannot be null")
	@NotBlank(message = "FI Name cannot be blank")
	@Size(max = 255, message = "FI Name cannot exceed 25 characters")
	private String fiName;

	private byte[] logo;

	private List<String> modules;

	private OfficeContactNumberRequest officeContactNumber;
	private AlternateNumberRequest alternateNumber;

	private AddressRequest address;
}
