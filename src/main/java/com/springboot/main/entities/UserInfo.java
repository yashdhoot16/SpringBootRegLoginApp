package com.springboot.main.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String mobileNumber;
	@ManyToOne
	@JoinColumn(name = "user_credential_id")
	private UserCredential userCredential;
	@Column(nullable = false, columnDefinition = "CHAR(1) DEFAULT 'U'")
	private String userType;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public UserCredential getUserCredential() {
		return this.userCredential;
	}

	public void setUserCredential(UserCredential userCredential) {
		this.userCredential = userCredential;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		if (userType != null && (userType.equals("U") || userType.equals("A"))) {
			this.userType = userType;
		} else {
			throw new IllegalArgumentException("Invalid userType value. It must be 'U' or 'A'.");
		}
	}
}
