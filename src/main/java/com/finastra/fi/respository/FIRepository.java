package com.finastra.fi.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finastra.fi.dao.entity.FIDetails;

@Repository
public interface FIRepository extends JpaRepository<FIDetails, Long>{


}
