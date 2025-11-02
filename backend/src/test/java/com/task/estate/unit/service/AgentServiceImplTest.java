package com.task.estate.unit.service;

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
import com.task.estate.service.agent.AgentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AgentServiceImplTest {

	@Mock
	private AgentRepository agentRepository;
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private PropertyRepository propertyRepository;
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	@Mock
	private ConversionService conversionService;

	@InjectMocks
	private AgentServiceImpl agentService;

	private Agent agent;
	private Customer customer;
	private Property property;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		Authentication authentication = mock(Authentication.class);
		when(authentication.getName()).thenReturn("agent@test.com");
		SecurityContext securityContext = mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);

		agent = new Agent();
		agent.setId(1L);
		agent.setEmail("agent@test.com");

		customer = new Customer();
		customer.setId(1L);
		customer.setAgent(agent);

		property = new Property();
		property.setId(1L);
		property.setAgent(agent);
	}

	@Test
	void assignPropertyToCustomer_success() {
		when(agentRepository.findByEmailIgnoreCase("agent@test.com")).thenReturn(Optional.of(agent));
		when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));
		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		when(propertyRepository.save(any(Property.class))).thenReturn(property);
		when(conversionService.convert(any(Property.class), eq(PropertyDto.class)))
				.thenReturn(new PropertyDto(1L, "Berlin", "Spitalhofstraße 1", 1000.0, true, 1L, 1L));

		PropertyDto result = agentService.assignPropertyToCustomer(1L, 1L);

		assertNotNull(result);
		verify(propertyRepository).save(property);
		assertEquals(customer, property.getCustomer());
	}

	@Test
	void assignPropertyToCustomer_propertyNotFound() {
		when(agentRepository.findByEmailIgnoreCase("agent@test.com")).thenReturn(Optional.of(agent));
		when(propertyRepository.findById(1L)).thenReturn(Optional.empty());

		AgentException ex = assertThrows(AgentException.class,
				() -> agentService.assignPropertyToCustomer(1L, 1L));
		assertEquals(AgentMessageId.PROPERTY_NOT_FOUND, ex.getMessageId());
	}

	@Test
	void assignPropertyToCustomer_customerNotFound() {
		when(agentRepository.findByEmailIgnoreCase("agent@test.com")).thenReturn(Optional.of(agent));
		when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));
		when(customerRepository.findById(1L)).thenReturn(Optional.empty());

		AgentException ex = assertThrows(AgentException.class,
				() -> agentService.assignPropertyToCustomer(1L, 1L));
		assertEquals(AgentMessageId.CUSTOMER_NOT_FOUND, ex.getMessageId());
	}

	@Test
	void assignPropertyToCustomer_propertyNotOwnedByAgent() {
		Agent otherAgent = new Agent();
		otherAgent.setId(2L);
		Property foreignProperty = new Property();
		foreignProperty.setId(1L);
		foreignProperty.setAgent(otherAgent);

		when(agentRepository.findByEmailIgnoreCase("agent@test.com")).thenReturn(Optional.of(agent));
		when(propertyRepository.findById(1L)).thenReturn(Optional.of(foreignProperty));

		AgentException ex = assertThrows(AgentException.class,
				() -> agentService.assignPropertyToCustomer(1L, 1L));
		assertEquals(AgentMessageId.PROPERTY_NOT_OWNED_BY_AGENT, ex.getMessageId());
	}

	@Test
	void assignPropertyToCustomer_customerNotOwnedByAgent() {
		Agent otherAgent = new Agent();
		otherAgent.setId(2L);
		Customer foreignCustomer = new Customer();
		foreignCustomer.setId(1L);
		foreignCustomer.setAgent(otherAgent);

		when(agentRepository.findByEmailIgnoreCase("agent@test.com")).thenReturn(Optional.of(agent));
		when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));
		when(customerRepository.findById(1L)).thenReturn(Optional.of(foreignCustomer));

		AgentException ex = assertThrows(AgentException.class,
				() -> agentService.assignPropertyToCustomer(1L, 1L));
		assertEquals(AgentMessageId.CUSTOMER_NOT_OWNED_BY_AGENT, ex.getMessageId());
	}

	@Test
	void addCustomer_success() {
		CreateCustomerRequestDto request = CreateCustomerRequestDto.builder()
				.name("John Doe")
				.email("john@example.com")
				.password("secret123")
				.build();

		Customer savedCustomer = new Customer();
		savedCustomer.setId(1L);
		savedCustomer.setName("John Doe");
		savedCustomer.setEmail("john@example.com");

		when(agentRepository.findByEmailIgnoreCase("agent@test.com")).thenReturn(Optional.of(agent));
		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
		when(passwordEncoder.encode(any(String.class))).thenReturn("password");
		when(conversionService.convert(savedCustomer, CustomerDto.class)).thenReturn(CustomerDto.builder()
				.id(1L)
				.name("John Doe")
				.email("john@example.com")
				.build());

		CustomerDto result = agentService.addCustomer(request);

		assertNotNull(result);
		assertEquals("John Doe", result.getName());
		verify(customerRepository).save(any(Customer.class));
		verify(conversionService).convert(savedCustomer, CustomerDto.class);
	}

	@Test
	void addCustomer_failure_shouldThrowAgentException() {
		CreateCustomerRequestDto request = CreateCustomerRequestDto.builder()
				.name("John Doe")
				.email("john@example.com")
				.password("secret123")
				.build();

		when(agentRepository.findByEmailIgnoreCase("agent@test.com"))
				.thenThrow(new RuntimeException("Database error"));

		assertThrows(AgentException.class, () -> agentService.addCustomer(request));
	}

	@Test
	void addProperty_success() {
		CreatePropertyRequestDto request = CreatePropertyRequestDto.builder()
				.title("Luxury Apartment")
				.address("Berlin Mitte")
				.price(250000.0)
				.isNewConstruction(true)
				.build();

		Property savedProperty = new Property();
		savedProperty.setId(1L);
		savedProperty.setTitle("Luxury Apartment");
		savedProperty.setAddress("Berlin Mitte");
		savedProperty.setPrice(250000.0);

		when(agentRepository.findByEmailIgnoreCase("agent@test.com")).thenReturn(Optional.of(agent));
		when(propertyRepository.save(any(Property.class))).thenReturn(savedProperty);
		when(conversionService.convert(savedProperty, PropertyDto.class)).thenReturn(PropertyDto.builder()
				.id(1L)
				.title("Luxury Apartment")
				.address("Berlin Mitte")
				.price(250000.0)
				.isNewConstruction(true)
				.agentId(1L)
				.build());

		PropertyDto result = agentService.addProperty(request);

		assertNotNull(result);
		assertEquals("Luxury Apartment", result.getTitle());
		verify(propertyRepository).save(any(Property.class));
		verify(conversionService).convert(savedProperty, PropertyDto.class);
	}

	@Test
	void addProperty_failure_shouldThrowAgentException() {
		CreatePropertyRequestDto request = CreatePropertyRequestDto.builder()
				.title("Luxury Apartment")
				.address("Berlin Mitte")
				.price(250000.0)
				.isNewConstruction(true)
				.build();

		when(agentRepository.findByEmailIgnoreCase("agent@test.com"))
				.thenThrow(new RuntimeException("Database error"));

		assertThrows(AgentException.class, () -> agentService.addProperty(request));
	}
}
