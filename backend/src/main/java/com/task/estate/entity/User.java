package com.task.estate.entity;

import com.task.estate.entity.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Table(
		name = "users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "email")
		}
)
public abstract class User extends BusinessEntity {

	@NotBlank
	@Column(nullable = false)
	private String name;

	@Email
	@NotBlank
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank
	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role = UserRole.CUSTOMER;
}
