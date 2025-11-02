package com.task.estate.service.authentication.token;
import com.task.estate.configuration.JwtConfiguration;
import com.task.estate.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider implements IJwtTokenProvider {

	private final JwtConfiguration jwtConfiguration;

	public JwtTokenProvider(JwtConfiguration jwtConfiguration) {
		this.jwtConfiguration = jwtConfiguration;
	}

	@Override
	public String generateAccessToken(User user) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + jwtConfiguration.getTokenExpiration() * 1000L);

		return Jwts.builder()
				.setSubject(user.getEmail())
				.claim("role", user.getRole().name())
				.setIssuedAt(now)
				.setExpiration(expiry)
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtConfiguration.getSecret());
		return Keys.hmacShaKeyFor(keyBytes);
	}
}