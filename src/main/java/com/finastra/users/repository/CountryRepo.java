package com.finastra.users.repository;

import com.finastra.users.dto.CountryNameAndCodeDto;
import com.finastra.users.dto.CountryNameAndPhoneCodeDto;
import com.finastra.users.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface CountryRepo extends JpaRepository<Country,Integer> {
    @Query("SELECT new com.finastra.users.dto.CountryNameAndCodeDto(p.countryCode, p.countryName) FROM Country p")
    Set<CountryNameAndCodeDto> findAllCountry();

    @Query("SELECT new com.finastra.users.dto.CountryNameAndPhoneCodeDto(p.phoneCode, p.countryName) FROM Country p")
    Set<CountryNameAndPhoneCodeDto> findAllCountryAndPhoneCode();

    Optional<Country> findByCountryCode(String countryCode);
}
