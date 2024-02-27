package com.finastra.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MobileDto {
    private Long id;
    private String countryCode;
    private String phoneNumber;
}
