package com.movi.user.service;

import com.movi.user.commons.ICrudService;
import com.movi.user.entity.BusinessDriver;
import com.movi.user.entity.BusinessDriverVehicle;

public interface IBusinessDriverVehicleService extends ICrudService<BusinessDriverVehicle, String> {

	public BusinessDriverVehicle findByBusinessDriverAndActiveAndEnable(String businessDriverId, boolean active, boolean enable);
}