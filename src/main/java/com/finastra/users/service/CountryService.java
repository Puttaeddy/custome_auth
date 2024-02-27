package com.finastra.users.service;

import com.finastra.users.dto.*;

import java.util.Set;

public interface CountryService {


    public Set<CountryNameAndCodeDto> getAllCountryAndCode();

    public Set<StateNameAndCodeDto> getAllStateAndCode(String countryCode);

    public Set<CityNameAndCodeDto> getAllCityAndCode(String countryCode, String stateCode);
}
