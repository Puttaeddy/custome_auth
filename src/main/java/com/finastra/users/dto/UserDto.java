package com.finastra.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finastra.fi.dao.dto.FIDetailsDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
        private long id;
        private FIDetailsDTO fiDetail;
        private String userName;
        private String firstName;

        private String lastName;

        private String email;
        private String password;

        private RoleDto role;
       private MobileDto mobileNumber;
       private AlternateNumberDto alternateNumber;
       private OfficeContactNumberDto officeContactNumber;

         private AddressDto address;




}
