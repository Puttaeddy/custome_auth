package com.finastra.users.service.impl;

import com.finastra.users.dao.RoleDao;
import com.finastra.users.dto.RoleDto;
import com.finastra.users.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleServiceDao;


    @Override
    public RoleDto getRole(Integer roleId) {
        return roleServiceDao.getRole(roleId);
    }

    @Override
    public List<RoleDto> getRoleByViewType(String viewType) {

        return roleServiceDao.getRoleByViewType(viewType);
    }

}

