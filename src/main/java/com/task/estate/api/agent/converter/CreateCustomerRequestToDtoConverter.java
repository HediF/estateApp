package com.task.estate.api.agent.converter;

import com.task.estate.domain.agent.CreateCustomerRequestDto;
import com.task.estate.agent.v1.gen.model.CreateCustomerRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerRequestToDtoConverter implements Converter<CreateCustomerRequest, CreateCustomerRequestDto> {

	@Override
	public CreateCustomerRequestDto convert(CreateCustomerRequest source) {
		return CreateCustomerRequestDto.builder()
				.name(source.getName())
				.email(source.getEmail())
				.password(source.getPassword())
				.build();
	}
}
