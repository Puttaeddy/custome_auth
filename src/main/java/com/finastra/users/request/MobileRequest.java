package com.finastra.users.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MobileRequest {
    private Long id;
    @NotEmpty(message = "Country code is required")

    private String countryCode;
    @Size(min = 10, max = 10,message= "number must be of 10 digits")
    @NotEmpty(message = "Phone number is required and Number should contain 10 digits.")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Invalid phone number,must be of 10 digits")
    private String phoneNumber;
}
