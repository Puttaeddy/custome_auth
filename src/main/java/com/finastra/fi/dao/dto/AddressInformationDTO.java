package com.finastra.fi.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class AddressInformationDTO {

    private Long id;

    private String address1;

    private String address2;

    private String state;

    private String pinCode;

    private String country;
    
    
}
