package com.finastra.fi.response;

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
public class FIModuleResponse {

	private long id;
	private String moduleName;
	private String description;
	private boolean defaultModule;
}
