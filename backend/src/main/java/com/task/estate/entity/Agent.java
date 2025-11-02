package com.task.estate.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "agents")
public class Agent extends User {

	@OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
	private List<Customer> customers;

	@OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
	private List<Property> properties;
}
