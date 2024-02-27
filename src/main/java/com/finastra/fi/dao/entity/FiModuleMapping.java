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

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "fi_module_mapping")
@NamedQuery(name="FiModuleMapping.findAll", query="SELECT f FROM FiModuleMapping f")
public class FiModuleMapping {


	//Removed as per the comments
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "fi_id")
	private Long fiId;

	@Column(name = "op_id")
	private String opId;

	@Column(name = "module_id")
	private Long moduleId;


}
