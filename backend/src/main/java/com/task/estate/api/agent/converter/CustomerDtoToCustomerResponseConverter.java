package com.task.estate.api.agent.converter;

import com.task.estate.agent.v1.gen.model.CustomerResponse;
import com.task.estate.agent.v1.gen.model.SimplifiedProperty;
import com.task.estate.domain.Property.SimplifiedPropertyDto;
import com.task.estate.domain.customer.CustomerDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerDtoToCustomerResponseConverter implements Converter<CustomerDto, CustomerResponse> {

	@Override
	public CustomerResponse convert(CustomerDto source) {
		List<SimplifiedProperty> properties = source.getProperties() == null
				? List.of()
				: source.getProperties().stream()
				.map(this::toApiProperty)
				.collect(Collectors.toList());

		CustomerResponse response = new CustomerResponse();
		response.setId(source.getId() != null ? source.getId().intValue() : null);
		response.setName(source.getName());
		response.setEmail(source.getEmail());
		response.setProperties(properties);
		return response;
	}

	private SimplifiedProperty toApiProperty(SimplifiedPropertyDto dto) {
		SimplifiedProperty api = new SimplifiedProperty();
		api.setId(dto.getId() != null ? dto.getId().intValue() : null);
		return api;
	}
}
