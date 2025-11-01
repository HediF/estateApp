package com.task.estate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer extends User {

	@ManyToOne
	@JoinColumn(name = "agent_id")
	private Agent agent;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Property> properties;
}
