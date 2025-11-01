package com.task.estate.domain.agent;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class CreateCustomerRequestDto {
	private final String name;
	private final String email;
	private final String password;
}
