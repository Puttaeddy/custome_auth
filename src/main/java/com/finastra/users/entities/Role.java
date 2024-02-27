package com.finastra.users.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role {

	@Id

	private int id;

	private String code;
	private String name;

	private String roleType;




}
