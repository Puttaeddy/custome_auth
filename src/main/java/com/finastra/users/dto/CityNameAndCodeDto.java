package com.finastra.users.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString

public class CityNameAndCodeDto {

    private final String   cityName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityNameAndCodeDto that = (CityNameAndCodeDto) o;
        return Objects.equals(cityName, that.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName);
    }
}
