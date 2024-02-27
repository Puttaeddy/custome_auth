package com.finastra.fi.service;

import java.util.List;

import com.finastra.fi.dao.dto.FIDetailsDTO;
import com.finastra.fi.dao.dto.FIModulesDTO;
import com.finastra.fi.response.FIPagingResponse;
	
public interface FIService {

	FIPagingResponse getAllFinanacialInstitutionsWithPagination(Integer offset, Integer limit);
	
	FIDetailsDTO getFinanacialInstitutionById(Long id);
	
	FIDetailsDTO saveFinanacialInstitution(FIDetailsDTO fiDetailsEntity);

	FIDetailsDTO updateFinanacialInstitution(Long id, FIDetailsDTO fiDetailsEntity);

	String deleteFinanacialInstitution(Long id);

	List<FIModulesDTO> getAllModules();
	
}
