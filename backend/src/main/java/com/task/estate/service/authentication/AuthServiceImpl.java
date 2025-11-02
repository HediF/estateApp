package com.task.estate.service.authentication;

import com.task.estate.configuration.RegistrationConfiguration;
import com.task.estate.domain.UserType;
import com.task.estate.domain.authentication.LoginResponseDto;
import com.task.estate.entity.User;
import com.task.estate.entity.enums.UserRole;
import com.task.estate.exception.authentication.AuthenticationException;
import com.task.estate.exception.authentication.AuthenticationMessageId;
import com.task.estate.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.task.estate.domain.authentication.AgentRegistrationRequestDto;
import com.task.estate.domain.authentication.LoginRequestDto;
import com.task.estate.service.authentication.token.JwtTokenProvider;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

	private final AgentRepository agentRepository;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final RegistrationConfiguration registrationConfiguration;

	@Autowired
	public AuthServiceImpl(
			AgentRepository agentRepository,
			AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder,
			JwtTokenProvider jwtTokenProvider, RegistrationConfiguration registrationConfiguration) {
		this.agentRepository = agentRepository;
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.registrationConfiguration = registrationConfiguration;
	}

	@Override
	public com.task.estate.domain.agent.Agent registerAgent(AgentRegistrationRequestDto request) {
		log.info("[AUTH] Registering agent with email: {}", request.getEmail());
		try {
			String email = request.getEmail().trim().toLowerCase();

			String expectedCode = registrationConfiguration.getAgentVerificationCode();
			if (request.getRegistrationCode() == null ||
					!request.getRegistrationCode().equals(expectedCode)) {
				log.error("[AUTH] Invalid verification code for {}", email);
				throw new AuthenticationException(AuthenticationMessageId.INVALID_VERIFICATION_CODE);
			}

			if (agentRepository.existsByEmailIgnoreCase(email)) {
				log.error("[AUTH] Email already exists: {}", email);
				throw new AuthenticationException(AuthenticationMessageId.USER_ALREADY_EXISTS, email);
			}

			String hashedPassword = passwordEncoder.encode(request.getPassword());

			com.task.estate.entity.Agent entity = new com.task.estate.entity.Agent();
			entity.setName(request.getName());
			entity.setEmail(email);
			entity.setPassword(hashedPassword);
			entity.setRole(UserRole.AGENT);

			com.task.estate.entity.Agent saved = agentRepository.save(entity);
			String accessToken = jwtTokenProvider.generateAccessToken(saved);

			log.info("[AUTH] Agent successfully registered: {}", saved.getEmail());

			return com.task.estate.domain.agent.Agent.builder()
					.id(saved.getId())
					.name(saved.getName())
					.email(saved.getEmail())
					.accessToken(accessToken)
					.build();

		} catch (AuthenticationException e) {
			throw e;
		}
		catch (DataAccessException e) {
			log.error("[AUTH] Database error while registering agent {}", request.getEmail(), e);
			throw new AuthenticationException(AuthenticationMessageId.USER_AUTHENTICATION_FAILED, request.getEmail());
		} catch (Exception e) {
			log.error("[AUTH] Unexpected error during registration for {}", request.getEmail(), e);
			throw new AuthenticationException(AuthenticationMessageId.USER_AUTHENTICATION_FAILED, request.getEmail());
		}
	}

	@Override
	public LoginResponseDto login(LoginRequestDto request) {
		log.info("[AUTH] Authenticating user: {}", request.getEmail());

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail().trim(), request.getPassword()));

			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			User user = userDetails.getUser();

			String accessToken = jwtTokenProvider.generateAccessToken(user);

			log.info("[AUTH] User {} successfully authenticated", request.getEmail());

			return LoginResponseDto.builder()
					.userId(user.getId())
					.name(user.getName())
					.email(user.getEmail())
					.accessToken(accessToken)
					.userType(UserType.valueOf(user.getRole().name()))
					.build();

		} catch (org.springframework.security.core.AuthenticationException e) {
			log.error("[AUTH] Invalid credentials for user: {}", request.getEmail());
			throw new AuthenticationException(AuthenticationMessageId.INVALID_CREDENTIALS);
		} catch (Exception e) {
			log.error("[AUTH] Unexpected error during login for user: {}", request.getEmail(), e);
			throw new AuthenticationException(AuthenticationMessageId.USER_AUTHENTICATION_FAILED);
		}
	}
}
