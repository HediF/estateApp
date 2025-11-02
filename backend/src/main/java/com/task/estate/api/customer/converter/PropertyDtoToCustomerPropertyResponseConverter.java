package com.task.estate.api.customer.converter;

import com.task.estate.domain.Property.PropertyDto;
import com.task.estate.customer.v1.gen.model.PropertyResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PropertyDtoToCustomerPropertyResponseConverter implements Converter<PropertyDto, PropertyResponse> {

	@Override
	public PropertyResponse convert(PropertyDto source) {
		PropertyResponse response = new PropertyResponse();
		response.setId(source.getId().intValue());
		response.setTitle(source.getTitle());
		response.setAddress(source.getAddress());
		response.setPrice(source.getPrice());
		response.setIsNewConstruction(source.getIsNewConstruction());
		response.setCustomerId(source.getCustomerId().intValue());
		response.setAgentId(source.getAgentId().intValue());
		return response;
	}
}
