package com.finastra.users.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CountryStateCityDto {

    private Integer id;
    private String cityName;
    private Integer stateId;
    private String  stateCode;
    private String  stateName;
    private String  countryId;
    private String  countryCode;
    private String   countryName;
    private String   latitude;
    private String   longitude;

}
