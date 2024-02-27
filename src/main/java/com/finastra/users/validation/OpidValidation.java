package com.finastra.users.validation;

import com.finastra.fi.dao.entity.FIDetails;
import com.finastra.fi.respository.FIRepository;
import com.finastra.users.dao.RoleDao;
import com.finastra.users.dto.RoleDto;
import com.finastra.users.dto.UserDto;
import com.finastra.users.exception.MandatoryFieldException;
import com.finastra.users.exception.ResourceNotFoundException;
import com.finastra.users.exception.UserAlreadyExistException;
import com.finastra.users.repository.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpidValidation implements  Validators<UserDto> {
    private static final Logger LOGGER = LogManager.getLogger(OpidValidation.class);

    @Autowired
    private FIRepository fiRepository;
    @Autowired
    private  RoleDao roleDao;
    @Autowired
    private  UserRepo userRepo;

    @Override
    public boolean validate(UserDto dto) {
        if (userRepo.findByEmail(dto.getEmail()).isPresent()) {
            LOGGER.error("Email is already present in database : {} ", dto.getEmail());
            throw new UserAlreadyExistException(dto.getEmail());
        }
        RoleDto roleDto = roleDao.getRole(dto.getRole().getId());
        if (roleDto.getRoleType().equalsIgnoreCase("FI")) {
            if (dto.getFiDetail() == null ) {
                throw new MandatoryFieldException("fi_id", "Field is required for FI User");
            }
            List<FIDetails> fiDetails = fiRepository.findAll();
            if (fiDetails.stream().noneMatch(details -> details.getId().equals(dto.getFiDetail().getId()))) {
                LOGGER.error("Invalid Opid {} ", dto.getId());

                throw new ResourceNotFoundException("Financial Institute details", "op_id",
                        dto.getId() + " so user cannot be created");
            }
        }

        return true;
    }
}

