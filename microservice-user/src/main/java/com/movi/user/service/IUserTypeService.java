package com.movi.user.service;

import com.movi.user.commons.ICrudService;
import com.movi.user.entity.UserType;

public interface IUserTypeService extends ICrudService<UserType, String> {
	
	UserType findByname(String name);
	
}