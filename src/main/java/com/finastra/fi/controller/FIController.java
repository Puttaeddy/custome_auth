package com.finastra.fi.controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.finastra.fi.constants.FIConstants;
import com.finastra.fi.dao.dto.FIDetailsDTO;
import com.finastra.fi.dao.dto.FIModulesDTO;
import com.finastra.fi.dao.entity.FIModules;
import com.finastra.fi.request.FIRequest;
import com.finastra.fi.response.FIModuleResponse;
import com.finastra.fi.response.FIPagingResponse;
import com.finastra.fi.response.FIResponse;
import com.finastra.fi.service.FIService;
import com.finastra.fi.utility.EntityConverterUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/financialinstitutions")
public class FIController {

	@Autowired
	private FIService service;

	@Operation(summary = "Get all FIDetails entities", responses = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved FIDetails list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FIDetailsDTO.class)))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })

	@GetMapping
	public ResponseEntity<FIPagingResponse>  getAllFinancialInstitutionsDetails(
			@RequestParam(value = "offset", defaultValue = FIConstants.PAGE_NUMBER, required = false) Integer offset,
			@RequestParam(value = "limit", defaultValue = FIConstants.PAGE_SIZE, required = false) Integer limit){
		System.out.println("offset and limit " + offset + " " + limit);

			FIPagingResponse fidetailsResponse= service.getAllFinanacialInstitutionsWithPagination(offset, limit);

			return new ResponseEntity<>(fidetailsResponse, HttpStatus.OK);
	}

	@Operation(summary = "Get FIDetails entity by ID", responses = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved FIDetails data", content = @Content(schema = @Schema(implementation = FIDetailsDTO.class))),
			@ApiResponse(responseCode = "404", description = "FIDetails data not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@GetMapping("/{id}")
	public ResponseEntity<FIResponse> getFinanacialInstitutionById(@PathVariable Long id) {

		FIDetailsDTO fidetailsDTO = service.getFinanacialInstitutionById(id);

		FIResponse response = EntityConverterUtil.convertDTOToResponse(fidetailsDTO, FIResponse.class);
		
			return ResponseEntity.ok(response);
		
	}

	@Operation(summary = "Save FIDetails entity", responses = {
			@ApiResponse(responseCode = "200", description = "Successfully saved FIDetails data", content = @Content(schema = @Schema(implementation = FIDetailsDTO.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })

	@PostMapping(consumes = { "multipart/form-data", "application/json" })
	public ResponseEntity<FIResponse> saveFinancialInstitution(@RequestPart("logo") MultipartFile logo,
			@Valid @RequestPart("fiJsonParams") String fiRequestPayload) throws IOException {

		byte[] bytes = logo.getBytes();

		FIRequest fiRequest =  new ObjectMapper().readValue(fiRequestPayload, FIRequest.class);
		fiRequest.setLogo(bytes);
		System.out.println("Received Image: " + logo.getOriginalFilename());

		FIDetailsDTO requestDTo =  EntityConverterUtil.convertRequestToDTO(fiRequest, FIDetailsDTO.class);
		
		FIDetailsDTO responseDTO = service.saveFinanacialInstitution(requestDTo);

		FIResponse response = EntityConverterUtil.convertDTOToResponse(responseDTO, FIResponse.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}


	@Operation(summary = "Update FIDetails data by ID", responses = {
			@ApiResponse(responseCode = "201", description = "Successfully updated FIDetails data", content = @Content(schema = @Schema(implementation = FIDetailsDTO.class))),
			@ApiResponse(responseCode = "404", description = "FIDetails data not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@PutMapping("/{id}")

	public ResponseEntity<FIResponse> updateFinanacialInstitution(@RequestPart("logo") MultipartFile logo,
			@Valid  @RequestPart("fiJsonParams") String fiRequestPayload, @PathVariable Long id) throws IOException {

		FIRequest fiRequest =  new ObjectMapper().readValue(fiRequestPayload, FIRequest.class);
		byte[] bytes = logo.getBytes();
        fiRequest.setLogo(bytes);
		System.out.println("Received Image: " + logo.getOriginalFilename());

		FIDetailsDTO requestDTO =  EntityConverterUtil.convertRequestToDTO(fiRequest, FIDetailsDTO.class);

		FIDetailsDTO responseDTO = service.updateFinanacialInstitution(id, requestDTO);

		FIResponse response = EntityConverterUtil.convertDTOToResponse(responseDTO, FIResponse.class);

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Delete FIDetails entity by ID", responses = {
			@ApiResponse(responseCode = "204", description = "Successfully deleted FIDetails data"),
			@ApiResponse(responseCode = "404", description = "FIDetails data not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFinancialInstitution(@PathVariable Long id) {
		String message = service.deleteFinanacialInstitution(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(message +" --   " + id );
	}

	@Operation(summary = "Get all FIModules", responses = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved FIModules list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FIModules.class)))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@GetMapping("/modules")
	public ResponseEntity<List<FIModuleResponse>> getAllModules() {
	List<FIModulesDTO> fiModules = service.getAllModules();

	List<FIModuleResponse> fiModuleList = EntityConverterUtil.convertDTOtListToResponseList(fiModules,
			FIModuleResponse.class);

	return ResponseEntity.ok(fiModuleList);
	}
}
