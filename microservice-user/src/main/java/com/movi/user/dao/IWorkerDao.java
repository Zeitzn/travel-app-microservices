package com.movi.user.dao;

import org.springframework.stereotype.Repository;

import com.movi.user.commons.IGenericDAO;
import com.movi.user.entity.Worker;

@Repository
public interface IWorkerDao extends IGenericDAO<Worker, String> {
}