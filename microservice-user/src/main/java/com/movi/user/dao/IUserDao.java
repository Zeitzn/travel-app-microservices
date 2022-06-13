package com.movi.user.dao;

import org.springframework.stereotype.Repository;

import com.movi.user.commons.IGenericDAO;
import com.movi.user.entity.User;

@Repository
public interface IUserDao extends IGenericDAO<User, String> {
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
	User findByUsernameOrEmail(String username, String email);
	
}