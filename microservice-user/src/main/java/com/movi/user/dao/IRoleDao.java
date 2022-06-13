package com.movi.user.dao;

import org.springframework.stereotype.Repository;

import com.movi.user.commons.IGenericDAO;
import com.movi.user.entity.Role;

@Repository
public interface IRoleDao extends IGenericDAO<Role, String> {
	Role findByName(String name);
}