package com.task.estate.converter;

import com.task.estate.domain.Property.PropertyDto;
import com.task.estate.entity.Property;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PropertyToPropertyDtoConverter implements Converter<Property, PropertyDto> {

	@Override
	public PropertyDto convert(Property source) {
		return PropertyDto.builder()
				.id(source.getId())
				.title(source.getTitle())
				.address(source.getAddress())
				.price(source.getPrice())
				.isNewConstruction(source.getIsNewConstruction())
				.customerId(source.getCustomer() != null ? source.getCustomer().getId() : null)
				.agentId(source.getAgent() != null ? source.getAgent().getId() : null)
				.build();
	}
}
