package com.finastra.users.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString

public class CountryNameAndPhoneCodeDto {
    private final String  phoneCode;
    private final String   countryName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryNameAndPhoneCodeDto that = (CountryNameAndPhoneCodeDto) o;
        return Objects.equals(phoneCode, that.phoneCode) && Objects.equals(countryName, that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneCode, countryName);
    }
}
