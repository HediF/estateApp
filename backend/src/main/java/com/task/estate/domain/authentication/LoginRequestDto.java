package com.task.estate.domain.authentication;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequestDto {
	private final String email;
	private final String password;
}

