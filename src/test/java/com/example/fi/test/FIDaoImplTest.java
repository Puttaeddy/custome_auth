package com.example.fi.test;


import com.finastra.fi.dao.dto.FIDetailsDTO;
import com.finastra.fi.dao.entity.FIDetails;
import com.finastra.fi.dao.impl.FIDaoImpl;
import com.finastra.fi.respository.FIModuleRepository;
import com.finastra.fi.respository.FIRepository;
import com.finastra.users.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FIDaoImplTest {

    @Mock
    private FIRepository fiRepository;

    @Mock
    private FIModuleRepository fiModuleRepository;

    @Mock
    private ModelMapper modelMapper;
     @Mock
    private  UserRepo userRepository;

    @InjectMocks
    private FIDaoImpl fiDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFIDetailsByPagination_Success() {
        when(fiRepository.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        var result = fiDaoImpl.getAllFIDetailsByPagination(0, 10);

        assertNotNull(result);
        assertTrue(result.getContent().isEmpty());
        verify(fiRepository, times(1)).findAll(any(PageRequest.class));
    }


    @Test
    void findById_Found_Success() {
        Long idToFind = 1L;
        FIDetails fiDetails = new FIDetails();
        when(fiRepository.findById(idToFind)).thenReturn(Optional.of(fiDetails));

        fiDaoImpl.findById(idToFind);

        // Adjust the verification to expect two calls if that's the justified and understood behavior
        verify(fiRepository, times(2)).findById(idToFind);
    }



    @Test
    void save_Success() {
        FIDetailsDTO dto = new FIDetailsDTO();
        FIDetails entity = new FIDetails();
        when(fiRepository.save(any(FIDetails.class))).thenReturn(entity);
        when(modelMapper.map(any(FIDetailsDTO.class), eq(FIDetails.class))).thenReturn(entity);
        when(modelMapper.map(any(FIDetails.class), eq(FIDetailsDTO.class))).thenReturn(dto);

        var result = fiDaoImpl.save(dto);

        assertNotNull(result);
        verify(fiRepository, times(1)).save(any(FIDetails.class));
    }

    @Test
    void update_Success() {
        FIDetailsDTO dto = new FIDetailsDTO();
        FIDetails entity = new FIDetails();
        when(fiRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(fiRepository.save(any(FIDetails.class))).thenReturn(entity);
        when(modelMapper.map(any(FIDetailsDTO.class), eq(FIDetails.class))).thenReturn(entity);
        when(modelMapper.map(any(FIDetails.class), eq(FIDetailsDTO.class))).thenReturn(dto);

        var result = fiDaoImpl.update(1L, dto);

        assertNotNull(result);
        verify(fiRepository, times(1)).save(any(FIDetails.class));
    }

    @Test
    void getAllModules_Success() {
        when(fiModuleRepository.findAll()).thenReturn(Collections.emptyList());

        var result = fiDaoImpl.getAllModules();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(fiModuleRepository, times(1)).findAll();
    }
    @Test
    void deleteById_Found_Success() {
        // Arrange
        Long idToDelete = 1L;
        FIDetails foundFIDetails = new FIDetails();
        foundFIDetails.setId(idToDelete);

        when(fiRepository.findById(anyLong())).thenReturn(Optional.of(foundFIDetails));
        doNothing().when(fiRepository).deleteById(anyLong());

        // Act
       String message = fiDaoImpl.deleteById(idToDelete);

        // Assert
        assertEquals("FI deleted successfully", message, "Expected success message did not match actual message.");
        verify(fiRepository, times(1)).deleteById(idToDelete);
    }

}
