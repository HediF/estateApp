package com.task.estate.repository;

import com.task.estate.entity.Agent;
import com.task.estate.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByAgent(Agent agent);
	Optional<Customer> findByEmailIgnoreCase(String email);
}
