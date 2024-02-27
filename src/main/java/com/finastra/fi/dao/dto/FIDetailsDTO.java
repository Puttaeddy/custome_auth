package com.finastra.fi.dao.dto;

import java.util.List;

import com.finastra.users.dto.AddressDto;
import com.finastra.users.dto.AlternateNumberDto;
import com.finastra.users.dto.OfficeContactNumberDto;
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
public class FIDetailsDTO {

	private Long id;

	private String opId;

	private String fiName;

	private byte[] logo;

	private List<String> modules;

	private List<FiModuleMappingDTO> fiModuleMapping;

	private OfficeContactNumberDto officeContactNumber;
	private AlternateNumberDto alternateNumber;

	private AddressDto address;

}