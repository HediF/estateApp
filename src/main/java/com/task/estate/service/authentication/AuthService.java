package com.task.estate.service.authentication;

import com.task.estate.domain.agent.Agent;
import com.task.estate.domain.authentication.AgentRegistrationRequestDto;
import com.task.estate.domain.authentication.LoginRequestDto;
import com.task.estate.domain.authentication.LoginResponseDto;

public interface AuthService {

	Agent registerAgent(AgentRegistrationRequestDto request);

	LoginResponseDto login(LoginRequestDto request);
}
