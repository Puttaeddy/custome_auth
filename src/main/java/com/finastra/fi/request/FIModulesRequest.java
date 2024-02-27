package com.finastra.fi.request;

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
public class FIModulesRequest {

	private long id;
	private String moduleName;
	private String description;
	private boolean defaultModule;
}
