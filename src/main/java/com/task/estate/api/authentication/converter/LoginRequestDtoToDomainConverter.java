package com.task.estate.api.authentication.converter;

import com.task.estate.domain.authentication.LoginRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LoginRequestDtoToDomainConverter implements Converter<com.task.estate.authentication.v1.gen.model.LoginRequest, LoginRequestDto> {

	@Override
	public LoginRequestDto convert(com.task.estate.authentication.v1.gen.model.LoginRequest source) {
		return LoginRequestDto.builder()
				.email(source.getEmail())
				.password(source.getPassword())
				.build();
	}
}
