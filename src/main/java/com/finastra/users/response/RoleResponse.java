package com.finastra.users.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleResponse {
    private int id;
    private String code;
    private String name;
    @JsonIgnore
    private String roleType;
}
