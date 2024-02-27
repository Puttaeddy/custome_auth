package com.finastra.fi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class FiModuleMappingRequest{

	private Long id;

	private String opId;

	private Long fiId;

	private Long moduleId;



}
