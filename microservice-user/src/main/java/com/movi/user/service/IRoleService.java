package com.movi.user.service;

import com.movi.user.commons.ICrudService;
import com.movi.user.entity.Role;

public interface IRoleService extends ICrudService<Role, String> {
	
	Role findByname(String name);
	
}