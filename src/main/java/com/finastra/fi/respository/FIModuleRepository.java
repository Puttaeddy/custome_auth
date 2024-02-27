package com.finastra.fi.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finastra.fi.dao.entity.FIModules;


public interface FIModuleRepository extends JpaRepository<FIModules, Long> {

}
