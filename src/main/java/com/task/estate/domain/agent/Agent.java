package com.task.estate.domain.agent;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Agent {
	private final Long id;
	private final String name;
	private final String email;
	private final String accessToken;
}
