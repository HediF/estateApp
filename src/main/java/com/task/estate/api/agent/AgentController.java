package com.task.estate.api.agent;

import com.task.estate.agent.v1.gen.model.*;
import com.task.estate.domain.Property.PropertyDto;
import com.task.estate.domain.agent.CreateCustomerRequestDto;
import com.task.estate.domain.agent.CreatePropertyRequestDto;
import com.task.estate.domain.customer.CustomerDto;
import com.task.estate.agent.v1.gen.api.DefaultApi;
import com.task.estate.service.agent.AgentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AgentController implements DefaultApi {

	private final AgentService agentService;
	private final ConversionService conversionService;

	@Override
	public ResponseEntity<CustomerResponse> addCustomer(CreateCustomerRequest request) {
		log.info("[AGENT] Adding new customer: {}", request.getEmail());

		CreateCustomerRequestDto domainRequest =
				conversionService.convert(request, CreateCustomerRequestDto.class);
		CustomerDto createdCustomer = agentService.addCustomer(domainRequest);

		CustomerResponse response =
				conversionService.convert(createdCustomer, CustomerResponse.class);
		return ResponseEntity.status(201).body(response);
	}

	@Override
	public ResponseEntity<PropertyResponse> addProperty(CreatePropertyRequest request) {
		log.info("[AGENT] Adding new property: {}", request.getTitle());

		CreatePropertyRequestDto domainRequest =
				conversionService.convert(request, CreatePropertyRequestDto.class);
		PropertyDto createdProperty = agentService.addProperty(domainRequest);

		PropertyResponse response =
				conversionService.convert(createdProperty, PropertyResponse.class);
		return ResponseEntity.status(201).body(response);
	}

	@Override
	public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
		log.info("[AGENT] Fetching all customers for authenticated agent");

		List<CustomerDto> customers = agentService.getAllCustomers();
		List<CustomerResponse> responseList = customers.stream()
				.map(c -> conversionService.convert(c, CustomerResponse.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(responseList);
	}

	@Override
	public ResponseEntity<List<PropertyResponse>> getAllProperties() {
		log.info("[AGENT] Fetching all properties for authenticated agent");

		List<PropertyDto> properties = agentService.getAllProperties();
		List<PropertyResponse> responseList = properties.stream()
				.map(p -> conversionService.convert(p, PropertyResponse.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(responseList);
	}

	@Override
	public ResponseEntity<PropertyResponse> assignPropertyToCustomer(
			Integer propertyId,
			AssignPropertyToCustomerRequest assignPropertyToCustomerRequest
	) {
		log.info("[AGENT] Assigning property {} to customer {}", propertyId, assignPropertyToCustomerRequest.getCustomerId());

		PropertyDto updatedProperty = agentService.assignPropertyToCustomer(
				propertyId.longValue(),
				assignPropertyToCustomerRequest.getCustomerId().longValue()
		);
		PropertyResponse response = conversionService.convert(updatedProperty, PropertyResponse.class);

		return ResponseEntity.ok(response);
	}

}
