package com.task.estate.service.agent;

import com.task.estate.domain.Property.PropertyDto;
import com.task.estate.domain.agent.CreateCustomerRequestDto;
import com.task.estate.domain.agent.CreatePropertyRequestDto;
import com.task.estate.domain.customer.CustomerDto;
import com.task.estate.entity.Agent;
import com.task.estate.entity.Customer;
import com.task.estate.entity.Property;
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

		Agent agent = getAuthenticatedAgent();

		Customer customer = new Customer();
		customer.setName(request.getName());
		customer.setEmail(request.getEmail());
		customer.setPassword(passwordEncoder.encode(request.getPassword()));
		customer.setAgent(agent);

		Customer savedCustomer = customerRepository.save(customer);

		CustomerDto customerDto = conversionService.convert(savedCustomer, CustomerDto.class);

		log.info("[SERVICE] Successfully added customer with ID {}", savedCustomer.getId());
		return customerDto;
	}

	@Override
	public PropertyDto addProperty(CreatePropertyRequestDto request) {
		log.info("[SERVICE] Adding property: {}", request.getTitle());

		Agent agent = getAuthenticatedAgent();

		Property property = new Property();
		property.setTitle(request.getTitle());
		property.setAddress(request.getAddress());
		property.setPrice(request.getPrice());
		property.setIsNewConstruction(Boolean.TRUE.equals(request.getIsNewConstruction()));
		property.setAgent(agent);

		Property savedProperty = propertyRepository.save(property);
		PropertyDto propertyDto = conversionService.convert(savedProperty, PropertyDto.class);

		log.info("[SERVICE] Successfully added property with ID {}", savedProperty.getId());
		return propertyDto;
	}

	@Override
	public List<CustomerDto> getAllCustomers() {
		log.info("[SERVICE] Fetching all customers for current agent");

		Agent agent = getAuthenticatedAgent();
		List<Customer> customers = customerRepository.findByAgent(agent);

		List<CustomerDto> result = customers.stream()
				.map(c -> conversionService.convert(c, CustomerDto.class))
				.collect(Collectors.toList());

		log.info("[SERVICE] Found {} customers for agent {}", result.size(), agent.getEmail());
		return result;
	}

	@Override
	public List<PropertyDto> getAllProperties() {
		log.info("[SERVICE] Fetching all properties for current agent");

		Agent agent = getAuthenticatedAgent();
		List<Property> properties = propertyRepository.findByAgent(agent);

		List<PropertyDto> result = properties.stream()
				.map(p -> conversionService.convert(p, PropertyDto.class))
				.collect(Collectors.toList());

		log.info("[SERVICE] Found {} properties for agent {}", result.size(), agent.getEmail());
		return result;
	}

	@Override
	public PropertyDto assignPropertyToCustomer(Long propertyId, Long customerId) {
		log.info("[SERVICE] Assigning property {} to customer {}", propertyId, customerId);

		Agent agent = getAuthenticatedAgent();

		Property property = propertyRepository.findById(propertyId)
				.orElseThrow(() -> new IllegalArgumentException("Property not found"));

		if (!property.getAgent().equals(agent)) {
			throw new IllegalStateException("You can only assign your own properties");
		}

		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new IllegalArgumentException("Customer not found"));

		if (!customer.getAgent().equals(agent)) {
			throw new IllegalStateException("Customer does not belong to this agent");
		}

		property.setCustomer(customer);
		Property updated = propertyRepository.save(property);

		log.info("[SERVICE] Property {} assigned to customer {}", updated.getId(), customerId);
		return conversionService.convert(updated, PropertyDto.class);
	}

	private Agent getAuthenticatedAgent() {
		String agentEmail = SecurityContextHolder.getContext().getAuthentication().getName();

		return agentRepository.findByEmailIgnoreCase(agentEmail)
				.orElseThrow(() -> {
					log.error("[SERVICE] No agent found for authenticated email '{}'", agentEmail);
					return new IllegalStateException("Authenticated agent not found for email: " + agentEmail);
				});
	}
}
