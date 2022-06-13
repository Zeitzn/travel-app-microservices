package com.movi.user.dao;

import org.springframework.stereotype.Repository;

import com.movi.user.commons.IGenericDAO;
import com.movi.user.entity.Business;

@Repository
public interface IBusinessDao extends IGenericDAO<Business, String> {
}