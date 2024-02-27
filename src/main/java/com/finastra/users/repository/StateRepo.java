package com.finastra.users.repository;

import com.finastra.users.dto.StateNameAndCodeDto;
import com.finastra.users.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface StateRepo extends JpaRepository<State,Integer> {

    @Query(value="SELECT new com.finastra.users.dto.StateNameAndCodeDto(p.stateCode, p.stateName) FROM State p where p.countryId =:id")
    Set<StateNameAndCodeDto> findByCountryId(Integer id);

    Optional <State> findByCountryIdAndStateCode(Integer id, String stateCode);
}
