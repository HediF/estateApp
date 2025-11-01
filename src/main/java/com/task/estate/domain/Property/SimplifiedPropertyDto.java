package com.task.estate.domain.Property;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class SimplifiedPropertyDto {
	private final Long id;
}
