package com.finastra.users.repository;

import com.finastra.users.dto.CityNameAndCodeDto;
import com.finastra.users.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CityRepo extends JpaRepository<City, Integer> {

    @Query(value = "SELECT new com.finastra.users.dto.CityNameAndCodeDto(p.cityName) FROM City p where p.stateId =:id")
    Set<CityNameAndCodeDto> findByStateId(Integer id);

}


