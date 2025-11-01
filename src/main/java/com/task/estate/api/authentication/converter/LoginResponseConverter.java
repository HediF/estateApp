package com.task.estate.api.authentication.converter;

import com.task.estate.authentication.v1.gen.model.AgentRegistrationResponse;
import com.task.estate.authentication.v1.gen.model.LoginResponse;
import com.task.estate.domain.agent.Agent;
import com.task.estate.domain.authentication.LoginResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LoginResponseConverter implements Converter<LoginResponseDto, LoginResponse> {

	@Override
	public LoginResponse convert(LoginResponseDto source) {
		LoginResponse response = new LoginResponse();
		response.setUserId(Math.toIntExact(source.getUserId()));
		response.setName(source.getName());
		response.setEmail(source.getEmail());
		response.setAccessToken(source.getAccessToken());
		response.setUserType(LoginResponse.UserTypeEnum.valueOf(source.getUserType().name()));
		return response;
	}
}
