package com.finastra.users.repository;

import com.finastra.users.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {

    public List<Role > findByRoleType(String roleType);

}
