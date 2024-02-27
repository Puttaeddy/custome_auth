package com.finastra.fi.dao.entity;

import java.util.List;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.finastra.users.entities.Address;
import com.finastra.users.entities.MobileNumber;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "fi_details")
@NamedQuery(name = "FIDetails.findAll", query = "SELECT f FROM FIDetails f")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class FIDetails extends BaseEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "op_id")
	private String opId;

	@Column(name = "fi_name")
	private String fiName;

	@Lob
	@Column(name = "logo")
	private byte[] logo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "office_contact_id")
	private MobileNumber officeContactNumber;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "alternate_mobile_number_id")
	private MobileNumber alternateNumber;

	@JsonIgnore
	@Transient
	private List<String> modules;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fi_id", referencedColumnName = "id")
	private List<FiModuleMapping> fiModuleMapping;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

}
