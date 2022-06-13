package com.movi.user.dao;

import org.springframework.stereotype.Repository;

import com.movi.user.commons.IGenericDAO;
import com.movi.user.entity.BusinessDriver;
import com.movi.user.entity.User;

@Repository
public interface IBusinessDriverDao extends IGenericDAO<BusinessDriver, String> {
	
	public BusinessDriver findByDriverIdAndActiveAndEnable(String driverId, boolean active, boolean enable);
	
}