package com.task.estate.converter;

import com.task.estate.domain.Property.SimplifiedPropertyDto;
import com.task.estate.domain.customer.CustomerDto;
import com.task.estate.entity.Customer;
import com.task.estate.entity.Property;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerToCustomerDtoConverter implements Converter<Customer, CustomerDto> {

	@Override
	public CustomerDto convert(Customer source) {
		List<SimplifiedPropertyDto> propertyDtos = source.getProperties() == null
				? List.of()
				: source.getProperties().stream()
				.map(this::convertProperty)
				.collect(Collectors.toList());

		return CustomerDto.builder()
				.id(source.getId())
				.name(source.getName())
				.email(source.getEmail())
				.properties(propertyDtos)
				.build();
	}

	private SimplifiedPropertyDto convertProperty(Property property) {
		return SimplifiedPropertyDto.builder()
				.id(property.getId())
				.build();
	}
}
