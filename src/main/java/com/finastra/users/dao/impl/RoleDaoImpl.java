package com.finastra.users.dao.impl;

import com.finastra.users.dao.RoleDao;
import com.finastra.users.dto.RoleDto;
import com.finastra.users.entities.Role;
import com.finastra.users.exception.ResourceNotFoundException;
import com.finastra.users.repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleDaoImpl implements RoleDao {
    private  ModelMapper modelMapper;

    private RoleRepo roleRepo;
    @Autowired
    public RoleDaoImpl(ModelMapper modelMapper, RoleRepo roleRepo) {
        this.modelMapper = modelMapper;
        this.roleRepo = roleRepo;
    }


    @Override
    public RoleDto getRole(Integer roleId) {
        Optional<Role> role=  Optional.ofNullable(roleRepo.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role", " roleId ", roleId.toString())));
        return  convertEntityToDto(role.get());
    }

    @Override
    public List<RoleDto> getRoleByViewType(String viewType) {
      List<Role> roles=  roleRepo.findByRoleType(viewType);
      if(roles.isEmpty()){
          throw new ResourceNotFoundException("Role", " viewType ", viewType);
      }
        return roles.stream().map(role->modelMapper.map(role,RoleDto.class)).toList();
    }


    private  RoleDto convertEntityToDto(Role role) {

        return modelMapper.map(role,RoleDto.class);
    }


}
