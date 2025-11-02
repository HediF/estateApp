package com.task.estate.util.jwt;

import com.task.estate.configuration.JwtConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.function.Function;

@Component
public class JwtUtil {

	private final Key key;

	public JwtUtil(JwtConfiguration jwtConfiguration) {
		byte[] keyBytes = Decoders.BASE64.decode(jwtConfiguration.getSecret());
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	public boolean validateToken(String token) {
		try {
			parseClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private Jws<Claims> parseClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = parseClaims(token).getBody();
		return claimsResolver.apply(claims);
	}
}
