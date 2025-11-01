package com.task.estate.domain.agent;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class CreatePropertyRequestDto {
	private final String title;
	private final String address;
	private final Double price;
	private final Boolean isNewConstruction;
	private final Long customerId;
}
