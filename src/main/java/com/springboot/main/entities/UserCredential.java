package com.springboot.main.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class UserCredential {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_credential_id;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;

	public Long getId() {
		return this.user_credential_id;
	}

	public void setId(Long user_credential_id) {
		this.user_credential_id = user_credential_id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
