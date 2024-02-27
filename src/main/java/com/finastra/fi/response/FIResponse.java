package com.finastra.fi.response;

import java.util.List;

import com.finastra.fi.request.FiModuleMappingRequest;
import com.finastra.users.request.AddressRequest;
import com.finastra.users.request.AlternateNumberRequest;
import com.finastra.users.request.OfficeContactNumberRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class FIResponse {

	private Long id;

	private String opId;

	private String fiName;

	private byte[] logo;

	private List<String> modules;

	private List<FiModuleMappingRequest> fiModuleMapping;
	private OfficeContactNumberRequest officeContactNumber;
	private AlternateNumberRequest alternateNumber;

	private AddressRequest address;
}
