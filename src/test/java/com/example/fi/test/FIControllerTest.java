package com.example.fi.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.finastra.fi.controller.FIController;
import com.finastra.fi.dao.dto.FIDetailsDTO;
import com.finastra.fi.request.FIRequest;
import com.finastra.fi.response.FIPagingResponse;
import com.finastra.fi.response.FIResponse;
import com.finastra.fi.service.FIService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class FIControllerTest {

    @Mock
    private FIService serviceMock;

    @InjectMocks
    private FIController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllFinancialInstitutionsDetails() {
        FIPagingResponse pagingResponseMock = new FIPagingResponse();
        when(serviceMock.getAllFinanacialInstitutionsWithPagination(0, 10)).thenReturn(pagingResponseMock);

        ResponseEntity<FIPagingResponse> responseEntity = controller.getAllFinancialInstitutionsDetails(0, 10);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pagingResponseMock, responseEntity.getBody());
    }

    @Test
    public void testGetFinancialInstitutionById() {
        long id = 123L;
        FIDetailsDTO detailsDTO = new FIDetailsDTO();
        when(serviceMock.getFinanacialInstitutionById(id)).thenReturn(detailsDTO);

        ResponseEntity<FIResponse> responseEntity = controller.getFinanacialInstitutionById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void testSaveFinancialInstitution() throws IOException {
        FIDetailsDTO detailsDTO = new FIDetailsDTO();
        when(serviceMock.saveFinanacialInstitution(any(FIDetailsDTO.class))).thenReturn(detailsDTO);

        MockMultipartFile logoFile = new MockMultipartFile("logo", "logo.jpg", "image/jpeg", new byte[10]);
        FIRequest fiRequest = new FIRequest();

        ResponseEntity<FIResponse> responseEntity = controller.saveFinancialInstitution(logoFile, new ObjectMapper().writeValueAsString(fiRequest));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void testUpdateFinancialInstitution() throws IOException {
        FIDetailsDTO detailsDTO = new FIDetailsDTO();
        when(serviceMock.updateFinanacialInstitution(anyLong(), any(FIDetailsDTO.class))).thenReturn(detailsDTO);

        MockMultipartFile logoFile = new MockMultipartFile("logo", "logo.jpg", "image/jpeg", new byte[10]);
        FIRequest fiRequest = new FIRequest();

        ResponseEntity<FIResponse> responseEntity = controller.updateFinanacialInstitution(logoFile, new ObjectMapper().writeValueAsString(fiRequest), 123L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }


}
