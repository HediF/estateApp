package com.task.estate.api.agent.converter;

import com.task.estate.domain.agent.CreatePropertyRequestDto;
import com.task.estate.agent.v1.gen.model.CreatePropertyRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreatePropertyRequestToDtoConverter implements Converter<CreatePropertyRequest, CreatePropertyRequestDto> {

	@Override
	public CreatePropertyRequestDto convert(CreatePropertyRequest source) {
		return CreatePropertyRequestDto.builder()
				.title(source.getTitle())
				.address(source.getAddress())
				.price(source.getPrice())
				.isNewConstruction(source.getIsNewConstruction())
				.build();
	}
}
