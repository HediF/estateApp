package com.task.estate.service.agent;

import com.task.estate.domain.Property.PropertyDto;
import com.task.estate.domain.agent.CreateCustomerRequestDto;
import com.task.estate.domain.agent.CreatePropertyRequestDto;
import com.task.estate.domain.customer.CustomerDto;

import java.util.List;

public interface AgentService {

	CustomerDto addCustomer(CreateCustomerRequestDto request);

	PropertyDto addProperty(CreatePropertyRequestDto request);

	List<CustomerDto> getAllCustomers();

	List<PropertyDto> getAllProperties();

	PropertyDto assignPropertyToCustomer(Long propertyId, Long customerId);
}
