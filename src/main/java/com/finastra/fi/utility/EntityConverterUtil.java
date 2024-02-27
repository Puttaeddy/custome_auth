package com.finastra.fi.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.finastra.fi.dao.dto.FIDetailsDTO;
import com.finastra.fi.dao.entity.FIDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EntityConverterUtil {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	private static final ModelMapper modelMapper = new ModelMapper();

	public static <T, U> U convertEntityToDTO(T entity, Class<U> dtoClass) {
		return objectMapper.convertValue(entity, dtoClass);
	}

	public static <T, U> List<U> convertEntityListToDTOList(List<T> entityList, Class<U> dtoClass) {
		return entityList.stream().map(entity -> convertEntityToDTO(entity, dtoClass)).collect(Collectors.toList());
	}

	public static <T, U> U convertDTOtoEntity(T dto, Class<U> entityClass) {

		return objectMapper.convertValue(dto, entityClass);

	}

	public static <T, U> List<T> convertDTOListtoEntityList(List<U> dtoList, Class<T> entityClass) {
		return dtoList.stream().map(dto -> convertDTOtoEntity(dto, entityClass)).collect(Collectors.toList());
	}

	public static <T, U> U convertRequestToDTO(T request, Class<U> dtoClass) {
		return objectMapper.convertValue(request, dtoClass);
	}

	public static <T, U> List<U> convertRequestListToDTOList(List<T> requestList, Class<U> dtoClass) {
		return requestList.stream().map(entity -> convertRequestToDTO(entity, dtoClass)).collect(Collectors.toList());
	}
	
	public static <T, U> U convertDTOToResponse(T dto, Class<U> dtoClass) {
		return objectMapper.convertValue(dto, dtoClass);
	}

	public static <T, U> List<U> convertDTOtListToResponseList(List<T> dtoList, Class<U> dtoClass) {
		return dtoList.stream().map(entity -> convertDTOToResponse(entity, dtoClass)).collect(Collectors.toList());
	}
	
	public FIDetails convertDtotToEntity(FIDetailsDTO fiDetialsDto) {
		return modelMapper.map(fiDetialsDto, FIDetails.class);
	}

	public FIDetailsDTO convertEntityToDto(FIDetails fiDetailsentity) {

		return modelMapper.map(fiDetailsentity, FIDetailsDTO.class);
	}
}
