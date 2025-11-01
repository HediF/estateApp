package com.task.estate.service.authentication.token;

import com.task.estate.entity.User;

public interface IJwtTokenProvider {
	String generateAccessToken(User user);
}
