package com.finastra.users.entities;

import com.finastra.fi.dao.entity.FIDetails;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Users extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private long id;
	@OneToOne//(fetch=FetchType.LAZY)
	@JoinColumn(name="fi_id")
	private FIDetails fiDetail;

	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@OneToOne
	@JoinColumn(name="role_id")
	private Role role;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="mobile_number_id")
	private MobileNumber mobileNumber;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="office_contact_id")
	private MobileNumber officeContactNumber;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="alternate_mobile_number_id")
	private MobileNumber alternateNumber;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;






}
