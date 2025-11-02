package com.task.estate.service.agent;

import com.task.estate.domain.Property.PropertyDto;
import com.task.estate.domain.agent.CreateCustomerRequestDto;
import com.task.estate.domain.agent.CreatePropertyRequestDto;
import com.task.estate.domain.customer.CustomerDto;
import com.task.estate.entity.Agent;
import com.task.estate.entity.Customer;
import com.task.estate.entity.Property;
import com.task.estate.exception.agent.AgentException;
import com.task.estate.exception.agent.AgentMessageId;
import com.task.estate.repository.AgentRepository;
import com.task.estate.repository.CustomerRepository;
import com.task.estate.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AgentServiceImpl implements AgentService {
	private final AgentRepository agentRepository;
	private final CustomerRepository customerRepository;
	private final PropertyRepository propertyRepository;
	private final ConversionService conversionService;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public CustomerDto addCustomer(CreateCustomerRequestDto request) {
		log.info("[SERVICE] Adding customer: {}", request.getEmail());
		try {
			Agent agent = getAuthenticatedAgent();

			Customer customer = new Customer();
			customer.setName(request.getName());
			customer.setEmail(request.getEmail());
			customer.setPassword(passwordEncoder.encode(request.getPassword()));
			customer.setAgent(agent);

			Customer savedCustomer = customerRepository.save(customer);
			return conversionService.convert(savedCustomer, CustomerDto.class);
		} catch (Exception e) {
			log.error("[SERVICE] Failed to create customer: {}", e.getMessage(), e);
			throw new AgentException(AgentMessageId.CUSTOMER_CREATION_FAILED);
		}
	}

	@Override
	public PropertyDto addProperty(CreatePropertyRequestDto request) {
		log.info("[SERVICE] Adding property: {}", request.getTitle());
		try {
			Agent agent = getAuthenticatedAgent();

			Property property = new Property();
			property.setTitle(request.getTitle());
			property.setAddress(request.getAddress());
			property.setPrice(request.getPrice());
			property.setIsNewConstruction(Boolean.TRUE.equals(request.getIsNewConstruction()));
			property.setAgent(agent);

			Property savedProperty = propertyRepository.save(property);
			return conversionService.convert(savedProperty, PropertyDto.class);
		} catch (Exception e) {
			log.error("[SERVICE] Failed to create property: {}", e.getMessage(), e);
			throw new AgentException(AgentMessageId.PROPERTY_CREATION_FAILED);
		}
	}

	@Override
	public List<CustomerDto> getAllCustomers() {
		log.info("[SERVICE] Fetching all customers for current agent");

		Agent agent = getAuthenticatedAgent();
		List<Customer> customers = customerRepository.findByAgent(agent);

		return customers.stream()
				.map(c -> conversionService.convert(c, CustomerDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PropertyDto> getAllProperties() {
		log.info("[SERVICE] Fetching all properties for current agent");

		Agent agent = getAuthenticatedAgent();
		List<Property> properties = propertyRepository.findByAgent(agent);

		return properties.stream()
				.map(p -> conversionService.convert(p, PropertyDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PropertyDto assignPropertyToCustomer(Long propertyId, Long customerId) {
		log.info("[SERVICE] Assigning property {} to customer {}", propertyId, customerId);

		Agent agent = getAuthenticatedAgent();

		Property property = propertyRepository.findById(propertyId)
				.orElseThrow(() -> new AgentException(AgentMessageId.PROPERTY_NOT_FOUND));

		if (!property.getAgent().equals(agent)) {
			throw new AgentException(AgentMessageId.PROPERTY_NOT_OWNED_BY_AGENT);
		}

		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new AgentException(AgentMessageId.CUSTOMER_NOT_FOUND));

		if (!customer.getAgent().equals(agent)) {
			throw new AgentException(AgentMessageId.CUSTOMER_NOT_OWNED_BY_AGENT);
		}

		property.setCustomer(customer);
		Property updated = propertyRepository.save(property);

		return conversionService.convert(updated, PropertyDto.class);
	}

	private Agent getAuthenticatedAgent() {
		String agentEmail = SecurityContextHolder.getContext().getAuthentication().getName();

		return agentRepository.findByEmailIgnoreCase(agentEmail)
				.orElseThrow(() -> {
					log.error("[SERVICE] No agent found for authenticated email '{}'", agentEmail);
					return new AgentException(AgentMessageId.AUTHENTICATED_AGENT_NOT_FOUND, agentEmail);
				});
	}
}