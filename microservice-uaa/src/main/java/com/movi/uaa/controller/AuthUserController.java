package com.movi.uaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movi.uaa.dto.AuthUserDto;
import com.movi.uaa.dto.RequestDto;
import com.movi.uaa.dto.SignUpDto;
import com.movi.uaa.dto.TokenDto;
import com.movi.uaa.entity.AuthUser;
import com.movi.uaa.service.AuthUserService;

@RestController
@RequestMapping("/auth")
public class AuthUserController {
	
	@Autowired
	private AuthUserService service;
	
	@PostMapping("/login")
	public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto dto){
		TokenDto tokenDto = service.login(dto);
		if(tokenDto== null) 
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(tokenDto);
		
	}
	
	@PostMapping("/validate")
	public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto dto){
		TokenDto tokenDto = service.validate(token,dto);
		if(tokenDto== null) 
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(tokenDto);
	}

	@PostMapping("/create")
	public ResponseEntity<AuthUser> create(@RequestBody SignUpDto dto){
		AuthUser authUser = service.save(dto);
		if(authUser == null) 
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(authUser);
	}
}
