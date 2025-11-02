package com.task.estate.api.customer;

import com.task.estate.customer.v1.gen.api.DefaultApi;
import com.task.estate.customer.v1.gen.model.PropertyResponse;
import com.task.estate.domain.Property.PropertyDto;
import com.task.estate.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController implements DefaultApi {

	private final CustomerService customerService;
	private final ConversionService conversionService;

	@Override
	public ResponseEntity<List<PropertyResponse>> getCustomerProperties() {
		log.info("[CUSTOMER] Fetching properties for authenticated customer");

		List<PropertyDto> properties = customerService.getCustomerProperties();
		List<PropertyResponse> responseList = properties.stream()
				.map(p -> conversionService.convert(p, PropertyResponse.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(responseList);
	}
}
