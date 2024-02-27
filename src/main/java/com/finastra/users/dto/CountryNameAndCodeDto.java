package com.finastra.users.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString

public class CountryNameAndCodeDto {
    private final String  countryCode;
    private final String   countryName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryNameAndCodeDto that = (CountryNameAndCodeDto) o;
        return Objects.equals(countryCode, that.countryCode) && Objects.equals(countryName, that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, countryName);
    }
}
