package com.finastra.users.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="city_id")
    private Integer id;
    private String cityCode;
    private String cityName;
    private Integer stateId;
}
