package com.finastra.users.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="state_id")
    private Integer id;
    private String  stateCode;
    private String  stateName;
    private Integer countryId;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="state_id")
//    private City city;

}
