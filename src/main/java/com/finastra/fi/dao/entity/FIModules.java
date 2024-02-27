package com.finastra.fi.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fi_modules")
@NamedQuery(name = "FIModules.findAll", query = "SELECT f FROM FIModules f")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class FIModules {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "module_name")
	private String moduleName;

	@Column(name = "module_description")
	private String description;

	@Column(name = "is_default_module")
	private boolean defaultModule;
}
