package com.movi.user.service;

import com.movi.user.commons.ICrudService;
import com.movi.user.dto.BusinessDriverDto;
import com.movi.user.entity.BusinessDriver;
import com.movi.user.entity.User;

public interface IBusinessDriverService extends ICrudService<BusinessDriver, String> {

	public BusinessDriver signUp(BusinessDriverDto businessDriverDto);
	
	public BusinessDriver findByDriverAndActiveAndEnable(String driverId, boolean active, boolean enable);
	
}