package com.movi.user.dao;

import org.springframework.stereotype.Repository;

import com.movi.user.commons.IGenericDAO;
import com.movi.user.entity.BusinessVehicle;

@Repository
public interface IBusinessVehicleDao extends IGenericDAO<BusinessVehicle, String> {
}