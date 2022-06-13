package com.movi.user.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movi.user.commons.CrudImpl;
import com.movi.user.commons.IGenericDAO;
import com.movi.user.dao.IBusinessVehicleDao;
import com.movi.user.dto.BusinessVehicleDto;
import com.movi.user.dto.VehicleDto;
import com.movi.user.entity.BusinessVehicle;
import com.movi.user.entity.Vehicle;
import com.movi.user.service.IBusinessVehicleService;
import com.movi.user.service.IVehicleService;

@Service
public class BusinessVehicleServiceImpl extends CrudImpl<BusinessVehicle, String> implements IBusinessVehicleService {

	@Autowired
	private IVehicleService vehicleService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private IBusinessVehicleDao dao;

	@Override
	protected IGenericDAO<BusinessVehicle, String> dao() {
		return dao;
	}

	@Override
	public BusinessVehicle registerWithVehicle(BusinessVehicleDto businessVehicleDto) {
		VehicleDto vehicleDto = businessVehicleDto.getVehicle();
		Vehicle vehicleResult = vehicleService.register(mapper.map(vehicleDto, Vehicle.class));
		businessVehicleDto.setVehicle(mapper.map(vehicleResult, VehicleDto.class));		
		return dao.save(mapper.map(businessVehicleDto, BusinessVehicle.class));
	}

}