package com.movi.user.dao;

import org.springframework.stereotype.Repository;

import com.movi.user.commons.IGenericDAO;
import com.movi.user.entity.Vehicle;

@Repository
public interface IVehicleDao extends IGenericDAO<Vehicle, String> {
}