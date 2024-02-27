package com.finastra.users.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleDto {
    private int id;

    private String code;
    private String name;

    private String roleType;
}
