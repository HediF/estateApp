package com.task.estate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "properties")
public class Property extends BusinessEntity {

	private String title;
	private String address;
	private Double price;
	private Boolean isNewConstruction;

	@ManyToOne
	@JoinColumn(name = "agent_id")
	private Agent agent;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
}
