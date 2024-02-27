package com.finastra.fi.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.finastra.fi.dao.dto.FIDetailsDTO;
import com.finastra.fi.dao.dto.FIModulesDTO;

public interface FIDao {

	FIDetailsDTO findById(Long id);

	FIDetailsDTO save(FIDetailsDTO fiDetailsEntity);

	FIDetailsDTO update(Long id, FIDetailsDTO fiDetailsDTO);

	String deleteById(Long id);

	Page<FIDetailsDTO> getAllFIDetailsByPagination(Integer offset, Integer limit);

	List<FIModulesDTO> getAllModules();

}