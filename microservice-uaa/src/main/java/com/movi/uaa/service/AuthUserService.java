package com.movi.uaa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movi.uaa.dto.AuthUserDto;
import com.movi.uaa.dto.RequestDto;
import com.movi.uaa.dto.SignUpDto;
import com.movi.uaa.dto.TokenDto;
import com.movi.uaa.entity.AuthUser;
import com.movi.uaa.repository.IAuthUserRepository;
import com.movi.uaa.security.JwtProvider;

@Service
public class AuthUserService  {
	
	@Autowired
	private IAuthUserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	public AuthUser save(SignUpDto dto) {
		Optional<AuthUser> user = repository.findByUsername(dto.getUsername());
		if(user.isPresent()) return null;		
		
		String password = passwordEncoder.encode(dto.getPassword());
		AuthUser authUser = AuthUser.builder()
				.username(dto.getUsername())
				.password(password)
				.role(dto.getRole())
				.build();
		return repository.save(authUser);
	}
	
	
	public TokenDto login(AuthUserDto dto) {
		Optional<AuthUser> user = repository.findByUsername(dto.getUsername());
		if(!user.isPresent()) return null;
		
		if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
			return new TokenDto(jwtProvider.createToken(user.get()));
		}
		return null;
			
	}
	
	public TokenDto validate(String token, RequestDto dto) {
		if(!jwtProvider.validate(token,dto))
			return null;
		String username = jwtProvider.getUsernameFromToken(token);
		Optional<AuthUser> user = repository.findByUsername(username);
		
		if(!user.isPresent()) return null;
		
		return new TokenDto(token);
	}

}
