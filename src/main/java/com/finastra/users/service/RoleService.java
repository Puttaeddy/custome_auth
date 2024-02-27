package com.finastra.users.service;

import com.finastra.users.dto.RoleDto;

import java.util.List;

public interface RoleService {

    public RoleDto getRole(Integer roleId);

    public List<RoleDto> getRoleByViewType(String viewType);


}
