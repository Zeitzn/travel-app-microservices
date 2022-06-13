package com.movi.user.service;

import com.movi.user.commons.ICrudService;
import com.movi.user.dto.BusinessVehicleDto;
import com.movi.user.entity.BusinessVehicle;

public interface IBusinessVehicleService extends ICrudService<BusinessVehicle, String> {
	
	public BusinessVehicle registerWithVehicle(BusinessVehicleDto businessVehicleDto);
	
}