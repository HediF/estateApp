package com.task.estate.service.authentication;

import com.task.estate.entity.User;
import com.task.estate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmailIgnoreCase(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());

		Collection<GrantedAuthority> authorities = Collections.singletonList(authority);

		return new CustomUserDetails(user, authorities);
	}
}