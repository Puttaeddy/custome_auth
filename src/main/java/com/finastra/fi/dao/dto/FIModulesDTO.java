package com.finastra.fi.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class FIModulesDTO {

	private long id;
	private String moduleName;
	private String description;
	private boolean defaultModule;
}
