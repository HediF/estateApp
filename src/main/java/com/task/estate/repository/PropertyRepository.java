package com.task.estate.repository;

import com.task.estate.entity.Agent;
import com.task.estate.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
	List<Property> findByAgent(Agent agent);
}
