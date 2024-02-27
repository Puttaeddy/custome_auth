package com.finastra.users.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString

public class StateNameAndCodeDto {
    private final String  stateCode;
    private final String   stateName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateNameAndCodeDto that = (StateNameAndCodeDto) o;
        return Objects.equals(stateCode, that.stateCode) && Objects.equals(stateName, that.stateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateCode, stateName);
    }
}
