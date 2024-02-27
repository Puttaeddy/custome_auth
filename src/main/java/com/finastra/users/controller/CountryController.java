package com.finastra.users.controller;


import com.finastra.users.dto.CityNameAndCodeDto;
import com.finastra.users.dto.CountryNameAndCodeDto;
import com.finastra.users.dto.CountryNameAndPhoneCodeDto;
import com.finastra.users.dto.StateNameAndCodeDto;
import com.finastra.users.service.impl.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryServiceImpl newCountryServiceImpl;


    @GetMapping()
    public ResponseEntity<Set<CountryNameAndCodeDto>> getAllCountryAndCode() {
        Set<CountryNameAndCodeDto> dto = newCountryServiceImpl.getAllCountryAndCode();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{countryCode}/state")
    public ResponseEntity<Set<StateNameAndCodeDto>> getStateByCountryCode(@PathVariable("countryCode") String countryCode) {
        Set<StateNameAndCodeDto> dto = newCountryServiceImpl.getAllStateAndCode(countryCode);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{countryCode}/state/{stateCode}")
    public ResponseEntity<Set<CityNameAndCodeDto>> getCityByStateCode(@PathVariable("countryCode") String countryCode, @PathVariable("stateCode") String stateCode) {
        Set<CityNameAndCodeDto> dto = newCountryServiceImpl.getAllCityAndCode(countryCode, stateCode);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @GetMapping("/phonecode")
    public ResponseEntity<Set<CountryNameAndPhoneCodeDto>> getAllCountryAndPhoneCode() {
        Set<CountryNameAndPhoneCodeDto> dto = newCountryServiceImpl.getAllCountryAndPhoneCode();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
