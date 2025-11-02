package com.task.estate.repository;

import com.task.estate.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
	boolean existsByEmailIgnoreCase(String email);
	Optional<Agent> findByEmailIgnoreCase(String email);
}
