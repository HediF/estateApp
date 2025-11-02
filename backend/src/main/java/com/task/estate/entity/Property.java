package com.task.estate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Getter
@Setter
@Table(name = "properties")
public class Property extends BusinessEntity {

	@NotBlank
	@Column(nullable = false)
	private String title;

	@NotBlank
	@Column(nullable = false)
	private String address;

	@NotNull
	@PositiveOrZero
	@Column(nullable = false)
	private Double price;

	@NotNull
	@Column(name = "is_new_construction", nullable = false)
	private Boolean isNewConstruction = false;

	@ManyToOne(optional = false)
	@JoinColumn(name = "agent_id", nullable = false)
	private Agent agent;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
}
