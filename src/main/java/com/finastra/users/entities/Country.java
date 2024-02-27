package com.finastra.users.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="country_id")
    private Integer id;
    private String countryCode;
    private String countryName;
    private String phoneCode;
}
