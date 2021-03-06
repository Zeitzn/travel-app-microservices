package com.movi.uaa.security;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.movi.uaa.dto.RequestDto;
import com.movi.uaa.entity.AuthUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Autowired
	private RouteValidator routeValidator;
	
	@PostConstruct
	protected void init() {
		secret = Base64.getEncoder().encodeToString(secret.getBytes());
	}
	
	/**
	 * @see https://github.com/jwtk/jjwt#signing-key
	 * @param authUser
	 * @return
	 */
	public String createToken(AuthUser authUser) {
		Map<String, Object> claims = Jwts.claims().setSubject(authUser.getUsername());
		claims.put("id", authUser.getId());
		claims.put("role", authUser.getRole());
		Date now = new Date();
		Date exp = new Date(now.getTime()+3600000);
		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(exp)
				.signWith(key)
				.compact();
	}
	
	public boolean validate(String token, RequestDto dto) {
		try {
			SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
			Jwts.parserBuilder()		
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token);
		} catch (Exception e) {
			return false;
		}
		
		if(!isAdmin(token) && routeValidator.isAdminPath(dto))
			return false;
		return true;	    
	}
	
	
	public String getUsernameFromToken(String token) {
		try {
			SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
		} catch (Exception e) {
			return "bad  token";
		}
	}

	private boolean isAdmin(String token) {
		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role").equals("admin");
	}
	
}
