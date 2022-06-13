package com.movi.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movi.user.commons.CrudImpl;
import com.movi.user.commons.IGenericDAO;
import com.movi.user.dao.IUserDao;
import com.movi.user.entity.User;
import com.movi.user.service.IUserService;

@Service
public class UserServiceImpl extends CrudImpl<User, String> implements IUserService {
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private IUserDao dao;

	@Override
	protected IGenericDAO<User, String> dao() {
		return dao;
	}
		
	@Override
	public boolean existsByUsername(String username) {
		return dao.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return dao.existsByEmail(email);
	}

	@Override
	public User findByUsernameOrEmail(String username, String email) {
		return dao.findByUsernameOrEmail(username, email);
	}

}