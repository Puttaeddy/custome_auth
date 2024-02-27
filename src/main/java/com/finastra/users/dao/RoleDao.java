package com.finastra.users.dao;

import com.finastra.users.dto.RoleDto;

import java.util.List;

public interface RoleDao {

    public RoleDto getRole(Integer roleId);

    public List<RoleDto> getRoleByViewType(String viewType);

}
