package com.finastra.users.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name="conatct")
public class MobileNumber {
        @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String countryCode;
        private String phoneNumber;


}

