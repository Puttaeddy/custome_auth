package com.finastra.users.response;

import com.finastra.users.request.AddressRequest;
import com.finastra.users.request.AlternateNumberRequest;
import com.finastra.users.request.MobileRequest;
import com.finastra.users.request.OfficeContactNumberRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    private long id;
    private String opId;
    private String userName;
    private String firstName;

    private String lastName;
    private String email;
    private String password;
    private RoleResponse role;
    private MobileRequest mobileNumber;
    private AlternateNumberRequest alternateNumber;
    private OfficeContactNumberRequest officeContactNumber;

    private AddressRequest address;


}
