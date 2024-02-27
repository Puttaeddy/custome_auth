package com.finastra.fi.dao.impl;

import com.finastra.fi.dao.FIDao;
import com.finastra.fi.dao.dto.FIDetailsDTO;
import com.finastra.fi.dao.dto.FIModulesDTO;
import com.finastra.fi.dao.entity.FIDetails;
import com.finastra.fi.dao.entity.FIModules;
import com.finastra.fi.respository.FIModuleRepository;
import com.finastra.fi.respository.FIRepository;
import com.finastra.fi.utility.EntityConverterUtil;
import com.finastra.users.entities.Users;
import com.finastra.users.exception.ResourceNotFoundException;
import com.finastra.users.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class FIDaoImpl implements FIDao {

	@Autowired
	private FIRepository repository;

	@Autowired
	private FIModuleRepository fiModuleRepository;

	@Autowired
	private final ModelMapper modelMapper;
	private final UserRepo userRepository;

	@Override
	public Page<FIDetailsDTO> getAllFIDetailsByPagination(Integer offset, Integer limit) {

		Pageable p = PageRequest.of(offset, limit);

		Page<FIDetails> pageEntities = this.repository.findAll(p);

		// Map the entities to DTOs
		List<FIDetailsDTO> dtoList = pageEntities.getContent().stream()
				.map(entity -> modelMapper.map(entity, FIDetailsDTO.class)).collect(Collectors.toList());
		return new PageImpl<>(dtoList, pageEntities.getPageable(), pageEntities.getTotalElements());
	}

	@Override
	public String deleteById(Long id) {
		Optional<FIDetails> fiDetails =repository.findById(id);
		if (fiDetails.isPresent()) {
			repository.deleteById(id);
			return "FI deleted successfully";
		}
		return "No resource found in DB";

	}

	@Override
	public FIDetailsDTO findById(Long id) {
		Optional.ofNullable(repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("FIUser", " Id ", id.toString())));
		FIDetails fidetails = repository.findById(id).get();
		FIDetailsDTO fiDetailsDTO = EntityConverterUtil.convertEntityToDTO(fidetails, FIDetailsDTO.class);

		return fiDetailsDTO;
	}

	@Override
	public FIDetailsDTO save(FIDetailsDTO fiDetailsDTO) {
		FIDetails fiEntity = EntityConverterUtil.convertDTOtoEntity(fiDetailsDTO, FIDetails.class);
		repository.save(fiEntity);

		fiDetailsDTO = EntityConverterUtil.convertDTOtoEntity(fiEntity, FIDetailsDTO.class);
		return fiDetailsDTO;

	}

	@Override
	public FIDetailsDTO update(Long id, FIDetailsDTO fiDetailsDTO) {
		FIDetails financialInstitution = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("FI", " fiId ", id.toString()));
		FIDetails fiDetail = repository.save(EntityConverterUtil.convertDTOtoEntity(fiDetailsDTO, FIDetails.class));
		FIDetailsDTO fiDetailDTO = EntityConverterUtil.convertEntityToDTO(fiDetail, FIDetailsDTO.class);
		return fiDetailDTO;
	}

	

	@Override
	public List<FIModulesDTO> getAllModules() {

		List<FIModulesDTO> fiModuleDTO = new ArrayList<>();
		List<FIModules> fiModules = fiModuleRepository.findAll();
		fiModules.stream().forEach(
				fiModule -> fiModuleDTO.add(EntityConverterUtil.convertEntityToDTO(fiModule, FIModulesDTO.class)));
		return fiModuleDTO;
	}
	
}