package com.movi.user.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movi.user.commons.CrudImpl;
import com.movi.user.commons.IGenericDAO;
import com.movi.user.dao.IWorkerDao;
import com.movi.user.dto.BusinessDto;
import com.movi.user.dto.SignUpDto;
import com.movi.user.dto.WorkerDto;
import com.movi.user.entity.Business;
import com.movi.user.entity.Role;
import com.movi.user.entity.Worker;
import com.movi.user.entity.User;
import com.movi.user.service.IBusinessService;
import com.movi.user.service.IRoleService;
import com.movi.user.service.IWorkerService;
import com.movi.user.service.IUserService;

@Service
public class WorkerServiceImpl extends CrudImpl<Worker, String> implements IWorkerService {

	@Autowired
	private IWorkerDao dao;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IBusinessService businessService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper mapper;

	@Override
	protected IGenericDAO<Worker, String> dao() {
		return dao;
	}

	@Override
	public Worker signUp(WorkerDto workerDto) {
		BusinessDto businessDto = workerDto.getBusiness();
		SignUpDto userDto = workerDto.getUser();

		// Prepare and save user
		User user = mapper.map(userDto, User.class);
		user.setCode("LSOQBJSGDO");
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Set<Role> roles = user.getRoles().stream().map(x -> roleService.findByname(x.getName()))
				.collect(Collectors.toSet());
		user.setRoles(roles);
		User userResult = userService.register(user);

		// Prepare and save business
		Business business = mapper.map(businessDto, Business.class);
		business.setCode("SAODASODP");
		business.setDriversNumber(0);
		Business businessResult = businessService.register(business);
		
		//Prepare and save staff
		Worker staff = mapper.map(workerDto, Worker.class);
		staff.setUser(userResult);
		staff.setBusiness(businessResult);
		
		return this.register(staff);
	}

}