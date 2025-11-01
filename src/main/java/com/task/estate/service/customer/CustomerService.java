package com.task.estate.service.customer;

import com.task.estate.domain.Property.PropertyDto;

import java.util.List;

public interface CustomerService {
	List<PropertyDto> getCustomerProperties();
}
