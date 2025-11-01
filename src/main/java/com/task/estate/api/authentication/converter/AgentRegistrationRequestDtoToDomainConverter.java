package com.task.estate.api.authentication.converter;

import com.task.estate.authentication.v1.gen.model.AgentRegistrationRequest;
import com.task.estate.domain.authentication.AgentRegistrationRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AgentRegistrationRequestDtoToDomainConverter implements Converter<AgentRegistrationRequest, AgentRegistrationRequestDto> {

	@Override
	public AgentRegistrationRequestDto convert(AgentRegistrationRequest source) {
		return AgentRegistrationRequestDto.builder()
				.name(source.getName())
				.email(source.getEmail())
				.password(source.getPassword())
				.registrationCode(source.getRegistrationCode())
				.build();
	}
}
