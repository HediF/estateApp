package com.task.estate.api.agent.converter;

import com.task.estate.domain.Property.PropertyDto;
import com.task.estate.agent.v1.gen.model.PropertyResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PropertyDtoToPropertyResponseConverter implements Converter<PropertyDto, PropertyResponse> {

	@Override
	public PropertyResponse convert(PropertyDto source) {
		PropertyResponse response = new PropertyResponse();
		response.setId(source.getId().intValue());
		response.setTitle(source.getTitle());
		response.setAddress(source.getAddress());
		response.setPrice(source.getPrice());
		response.setIsNewConstruction(source.getIsNewConstruction());
		response.setAgentId(source.getAgentId().intValue());
		response.setCustomerId(source.getCustomerId() != null ? source.getCustomerId().intValue() : null);
		return response;
	}
}
