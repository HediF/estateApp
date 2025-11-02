package com.task.estate.api.authentication.converter;

import com.task.estate.authentication.v1.gen.model.AgentRegistrationResponse;
import com.task.estate.domain.agent.Agent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AgentToAgentResponseConverter implements Converter<Agent, AgentRegistrationResponse> {

	@Override
	public AgentRegistrationResponse convert(Agent source) {
		AgentRegistrationResponse response = new AgentRegistrationResponse();
		response.setId(Math.toIntExact(source.getId()));
		response.setName(source.getName());
		response.setEmail(source.getEmail());
		response.setAccessToken(source.getAccessToken());
		response.setUserType(AgentRegistrationResponse.UserTypeEnum.AGENT);
		return response;
	}
}
