package com.finastra.users.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    @JsonIgnore
     private Long id;
    @Size(min = 1,message= "FiId should valid number")
    @NotEmpty(message = "FiId can't be null or empty")
    @Pattern(regexp="\\d", message = "FiId should be valid number")
     private String fiId;

    @NotEmpty(message = "user name is required")

    private String userName;
    @NotEmpty(message = "first name is required")
    @Size(min = 3, message = "first name should have at least 3 characters")
    private String firstName;

    private String lastName;
    @NotEmpty(message = "email is required")
    @Email(message = "Invalid email address !!")
    private String email;
    @NotEmpty(message = "a password must be eight characters including one uppercase letter, one special character and alphanumeric characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "Password must contain at least one digit, one uppercase and one lowercase letter")
    private String password;

    @Size(min = 1,message= "RoleId should valid number")
    @NotEmpty(message = "RoleId can't be null or empty")
    @Pattern(regexp="\\d", message = "RoleId should be valid number")
    private String role;

    @Valid
    private MobileRequest mobileNumber;
    private AlternateNumberRequest alternateNumber;
    private OfficeContactNumberRequest officeContactNumber;

    private AddressRequest address;


}
