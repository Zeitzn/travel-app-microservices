package com.movi.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movi.user.commons.CrudImpl;
import com.movi.user.commons.IGenericDAO;
import com.movi.user.dao.IBusinessDriverVehicleDao;
import com.movi.user.entity.BusinessDriverVehicle;
import com.movi.user.service.IBusinessDriverVehicleService;

@Service
public class BusinessDriverVehicleServiceImpl extends CrudImpl<BusinessDriverVehicle, String> implements IBusinessDriverVehicleService {

	@Autowired
	private IBusinessDriverVehicleDao dao;

	@Override
	protected IGenericDAO<BusinessDriverVehicle, String> dao() {
		return dao;
	}

	@Override
	public BusinessDriverVehicle findByBusinessDriverAndActiveAndEnable(String businessDriverId, boolean active,
			boolean enable) {
		return dao.findByBusinessDriverIdAndActiveAndEnable(businessDriverId, active, enable);
	}

}