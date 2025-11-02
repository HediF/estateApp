package com.task.estate.domain.authentication;
import com.task.estate.domain.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponseDto {
	private final Long userId;
	private final String name;
	private final String email;
	private final String accessToken;
	private final UserType userType;
}
