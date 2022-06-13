package com.movi.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movi.user.commons.CrudImpl;
import com.movi.user.commons.IGenericDAO;
import com.movi.user.dao.IRoleDao;
import com.movi.user.entity.Role;
import com.movi.user.service.IRoleService;

@Service
public class RoleServiceImpl extends CrudImpl<Role, String> implements IRoleService {

	@Autowired
	private IRoleDao dao;

	@Override
	protected IGenericDAO<Role, String> dao() {
		return dao;
	}

	@Override
	public Role findByname(String name) {
		return dao.findByName(name);
	}

}