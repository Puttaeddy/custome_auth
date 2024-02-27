package com.finastra.users.service.impl;


import com.finastra.users.dto.*;

import com.finastra.users.service.CountryService;
import com.finastra.users.dao.impl.CountryDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDaoImpl countryDao;


    public Set<CountryNameAndCodeDto> getAllCountryAndCode() {
        return countryDao.getAllCountryAndCode();
    }

    public Set<CountryNameAndPhoneCodeDto> getAllCountryAndPhoneCode() {
        return countryDao.getAllCountryAndPhoneCode();
    }

    public Set<StateNameAndCodeDto> getAllStateAndCode(String countryCode) {
        return countryDao.getAllStateAndCode(countryCode);
    }

    public Set<CityNameAndCodeDto> getAllCityAndCode(String countryCode, String stateCode) {
        return countryDao.getAllCityAndCode(countryCode,stateCode);
    }
}
