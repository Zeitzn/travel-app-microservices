package com.movi.user.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movi.user.commons.CrudImpl;
import com.movi.user.commons.IGenericDAO;
import com.movi.user.dao.IBusinessDriverDao;
import com.movi.user.dto.BusinessDriverDto;
import com.movi.user.dto.SignUpDto;
import com.movi.user.entity.BusinessDriver;
import com.movi.user.entity.Role;
import com.movi.user.entity.User;
import com.movi.user.service.IBusinessDriverService;
import com.movi.user.service.IRoleService;
import com.movi.user.service.IUserService;

@Service
public class BusinessDriverServiceImpl extends CrudImpl<BusinessDriver, String> implements IBusinessDriverService {

	@Autowired
	private IBusinessDriverDao dao;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper mapper;

	@Override
	protected IGenericDAO<BusinessDriver, String> dao() {
		return dao;
	}

	@Override
	public BusinessDriver signUp(BusinessDriverDto businessDriverDto) {
		SignUpDto userDto = businessDriverDto.getDriver();

		// Prepare and save user
		User user = mapper.map(userDto, User.class);
		user.setCode("LSOQBJSGDO");
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Set<Role> roles = user.getRoles().stream().map(x -> roleService.findByname(x.getName()))
				.collect(Collectors.toSet());
		user.setRoles(roles);
		User userResult = userService.register(user);		
		
		//Prepare and save staff
		BusinessDriver businessDriver = mapper.map(businessDriverDto, BusinessDriver.class);
		businessDriver.setDriver(userResult);
		
		return this.register(businessDriver);
	}

	@Override
	public BusinessDriver findByDriverAndActiveAndEnable(String driverId, boolean active, boolean enable) {
		return dao.findByDriverIdAndActiveAndEnable(driverId, active, enable);
	}

}