package com.movi.user.dao;

import org.springframework.stereotype.Repository;

import com.movi.user.commons.IGenericDAO;
import com.movi.user.entity.BusinessDriverVehicle;

@Repository
public interface IBusinessDriverVehicleDao extends IGenericDAO<BusinessDriverVehicle, String> {
	
	public BusinessDriverVehicle findByBusinessDriverIdAndActiveAndEnable(String businessDriverId, boolean active, boolean enable);
	
}