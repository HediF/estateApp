package com.task.estate.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "registration")
@Getter
@Setter
public class RegistrationConfiguration {
	private String agentVerificationCode;
}
