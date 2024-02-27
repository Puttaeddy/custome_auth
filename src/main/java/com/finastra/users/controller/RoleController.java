package com.finastra.users.controller;

import com.finastra.users.consts.AppConstants;
import com.finastra.users.dto.RoleDto;
import com.finastra.users.response.RoleResponse;
import com.finastra.users.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    private ModelMapper modelMapper;
    @Autowired
    public RoleController(RoleService roleService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<List<RoleResponse>> getAllRoleDetail(
            @RequestParam(value = "roleView", defaultValue = AppConstants.ADMIN, required = false) String roleView)
    {
        List<RoleDto> roleDtos= roleService.getRoleByViewType(roleView);
        List<RoleResponse> roleResponses= roleDtos.stream().map(roleDto->modelMapper.map(roleDto,RoleResponse.class)).toList();
        return new ResponseEntity<>(roleResponses, HttpStatus.OK);
    }
}
