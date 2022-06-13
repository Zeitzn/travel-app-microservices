package com.movi.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movi.user.commons.CrudImpl;
import com.movi.user.commons.IGenericDAO;
import com.movi.user.dao.IBusinessDao;
import com.movi.user.entity.Business;
import com.movi.user.service.IBusinessService;

@Service
public class BusinessServiceImpl extends CrudImpl<Business, String> implements IBusinessService {

	@Autowired
	private IBusinessDao dao;

	@Override
	protected IGenericDAO<Business, String> dao() {
		return dao;
	}	

}