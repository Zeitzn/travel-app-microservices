package com.movi.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movi.user.commons.CrudImpl;
import com.movi.user.commons.IGenericDAO;
import com.movi.user.dao.IUserTypeDao;
import com.movi.user.entity.UserType;
import com.movi.user.service.IUserTypeService;

@Service
public class UserTypeServiceImpl extends CrudImpl<UserType, String> implements IUserTypeService {

	@Autowired
	private IUserTypeDao dao;

	@Override
	protected IGenericDAO<UserType, String> dao() {
		return dao;
	}

	@Override
	public UserType findByname(String name) {
		return dao.findByName(name);
	}

}