package com.movi.user.service;

import com.movi.user.commons.ICrudService;
import com.movi.user.entity.User;

public interface IUserService extends ICrudService<User, String> {
	
	User findByUsernameOrEmail(String username, String email);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
}