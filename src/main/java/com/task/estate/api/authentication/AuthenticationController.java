package com.task.estate.api.authentication;

import com.task.estate.authentication.v1.gen.api.DefaultApi;
import com.task.estate.authentication.v1.gen.model.AgentRegistrationRequest;
import com.task.estate.authentication.v1.gen.model.AgentRegistrationResponse;
import com.task.estate.authentication.v1.gen.model.LoginRequest;
import com.task.estate.authentication.v1.gen.model.LoginResponse;
import com.task.estate.domain.agent.Agent;
import com.task.estate.domain.authentication.AgentRegistrationRequestDto;
import com.task.estate.domain.authentication.LoginRequestDto;
import com.task.estate.domain.authentication.LoginResponseDto;
import com.task.estate.service.authentication.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationController implements DefaultApi {

	private final AuthService authService;
	private final ConversionService conversionService;

	@Override
	public ResponseEntity<AgentRegistrationResponse> registerAgent(AgentRegistrationRequest request) {
		log.info("[AUTH] Registering new agent: {}", request.getEmail());

		AgentRegistrationRequestDto domainRequest = conversionService.convert(request, AgentRegistrationRequestDto.class);
		Agent agent = authService.registerAgent(domainRequest);

		AgentRegistrationResponse response = conversionService.convert(agent, AgentRegistrationResponse.class);
		return ResponseEntity.status(201).body(response);
	}

	@Override
	public ResponseEntity<LoginResponse> login(LoginRequest request) {
		log.info("[AUTH] Login attempt for user: {}", request.getEmail());

		LoginRequestDto domainRequest = conversionService.convert(request, LoginRequestDto.class);
		LoginResponseDto loginResponseDto = authService.login(domainRequest);

		LoginResponse response = conversionService.convert(loginResponseDto, LoginResponse.class);

		return ResponseEntity.ok(response);
	}
}
