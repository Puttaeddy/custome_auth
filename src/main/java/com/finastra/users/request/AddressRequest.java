package com.finastra.users.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    private Long id;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String pincode;
    private String country;
}
