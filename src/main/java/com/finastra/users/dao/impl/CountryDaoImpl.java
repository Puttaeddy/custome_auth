package com.finastra.users.dao.impl;

import com.finastra.users.dao.CountryDao;
import com.finastra.users.dto.CityNameAndCodeDto;
import com.finastra.users.dto.CountryNameAndCodeDto;
import com.finastra.users.dto.CountryNameAndPhoneCodeDto;
import com.finastra.users.dto.StateNameAndCodeDto;
import com.finastra.users.entities.Country;
import com.finastra.users.entities.State;
import com.finastra.users.exception.ResourceNotFoundException;
import com.finastra.users.repository.CityRepo;
import com.finastra.users.repository.CountryRepo;
import com.finastra.users.repository.StateRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CountryDaoImpl implements CountryDao {

    public static final String STATE = "state";
    public static final String COUNTRY_CODE = "country code";
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CountryRepo countryRepo;
    @Autowired
    private StateRepo stateRepo;
    @Autowired
    private CityRepo cityRepo;

    @Override
    public Set<CountryNameAndCodeDto> getAllCountryAndCode() {
        return countryRepo.findAllCountry();
    }
    public Set<CountryNameAndPhoneCodeDto> getAllCountryAndPhoneCode() {
        return countryRepo.findAllCountryAndPhoneCode();
    }

    @Override
    public Set<StateNameAndCodeDto> getAllStateAndCode(String countryCode) {
        Country country=countryRepo.findByCountryCode(countryCode).orElseThrow(() -> new ResourceNotFoundException(STATE, " " + COUNTRY_CODE + " ", countryCode));
        Set<StateNameAndCodeDto> optionalState=stateRepo.findByCountryId(country.getId());
        if( optionalState.isEmpty()){
            throw  new ResourceNotFoundException(STATE, " " + COUNTRY_CODE + " ", countryCode);
        }
        return stateRepo.findByCountryId(country.getId());
    }

    @Override
    public Set<CityNameAndCodeDto> getAllCityAndCode(String countryCode, String stateCode) {
        Country country=countryRepo.findByCountryCode(countryCode).orElseThrow(() -> new ResourceNotFoundException("country", " " + COUNTRY_CODE + " ", countryCode));

        State state=stateRepo.findByCountryIdAndStateCode(country.getId(),stateCode).orElseThrow(() -> new ResourceNotFoundException(STATE, " " + COUNTRY_CODE + "  and stateCode", countryCode +" "+ stateCode));
        return cityRepo.findByStateId(state.getId());
    }
}
