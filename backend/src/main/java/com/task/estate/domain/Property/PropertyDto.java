package com.task.estate.domain.Property;

import lombok.*;

@AllArgsConstructor
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class PropertyDto {
	private final Long id;
	private final String title;
	private final String address;
	private final Double price;
	private final Boolean isNewConstruction;
	private final Long customerId;
	private final Long agentId;
}
