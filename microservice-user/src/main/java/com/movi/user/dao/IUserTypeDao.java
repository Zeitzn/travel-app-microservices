package com.movi.user.dao;

import org.springframework.stereotype.Repository;

import com.movi.user.commons.IGenericDAO;
import com.movi.user.entity.UserType;

@Repository
public interface IUserTypeDao extends IGenericDAO<UserType, String> {
	UserType findByName(String name);
}