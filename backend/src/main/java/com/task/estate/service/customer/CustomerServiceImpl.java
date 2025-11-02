package com.task.estate.service.customer;

import com.task.estate.domain.Property.PropertyDto;
import com.task.estate.entity.Customer;
import com.task.estate.entity.Property;
import com.task.estate.exception.customer.CustomerException;
import com.task.estate.exception.customer.CustomerMessageId;
import com.task.estate.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final ConversionService conversionService;

	@Override
	public List<PropertyDto> getCustomerProperties() {
		log.info("[SERVICE] Fetching properties for authenticated customer");

		String customerEmail = SecurityContextHolder.getContext().getAuthentication().getName();

		Customer customer = customerRepository.findByEmailIgnoreCase(customerEmail)
				.orElseThrow(() -> {
					log.error("[SERVICE] No customer found for authenticated email '{}'", customerEmail);
					return new CustomerException(CustomerMessageId.AUTHENTICATED_CUSTOMER_NOT_FOUND, customerEmail);
				});

		List<Property> properties = customer.getProperties();

		if (properties == null || properties.isEmpty()) {
			log.info("[SERVICE] No properties assigned to customer {}", customerEmail);
			throw new CustomerException(CustomerMessageId.NO_PROPERTIES_FOUND, customerEmail);
		}

		try {
			List<PropertyDto> propertyDtos = properties.stream()
					.map(p -> conversionService.convert(p, PropertyDto.class))
					.collect(Collectors.toList());

			log.info("[SERVICE] Found {} properties for customer {}", propertyDtos.size(), customerEmail);
			return propertyDtos;
		} catch (Exception e) {
			log.error("[SERVICE] Failed to convert property entities for customer {}", customerEmail, e);
			throw new CustomerException(CustomerMessageId.PROPERTY_CONVERSION_FAILED, customerEmail);
		}
	}
}
