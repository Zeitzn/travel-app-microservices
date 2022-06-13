package com.movi.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movi.user.commons.CrudImpl;
import com.movi.user.commons.IGenericDAO;
import com.movi.user.dao.IVehicleDao;
import com.movi.user.entity.Vehicle;
import com.movi.user.service.IVehicleService;

@Service
public class VehicleServiceImpl extends CrudImpl<Vehicle, String> implements IVehicleService {

	@Autowired
	private IVehicleDao dao;

	@Override
	protected IGenericDAO<Vehicle, String> dao() {
		return dao;
	}

}