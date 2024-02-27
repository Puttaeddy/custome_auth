package com.finastra.users.dao;

import com.finastra.users.dto.*;

import java.util.Set;

public interface CountryDao {
    public Set<CountryNameAndCodeDto> getAllCountryAndCode();

    public Set<StateNameAndCodeDto> getAllStateAndCode(String countryCode);

    public Set<CityNameAndCodeDto> getAllCityAndCode(String countryCode, String stateCode);
    public Set<CountryNameAndPhoneCodeDto> getAllCountryAndPhoneCode();

}
