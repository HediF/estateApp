package com.task.estate.domain.customer;

import com.task.estate.domain.Property.SimplifiedPropertyDto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class CustomerDto {
	private final Long id;
	private final String name;
	private final String email;
	private final List<SimplifiedPropertyDto> properties;
}
