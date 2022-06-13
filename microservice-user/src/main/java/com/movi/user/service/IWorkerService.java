package com.movi.user.service;

import com.movi.user.commons.ICrudService;
import com.movi.user.dto.WorkerDto;
import com.movi.user.entity.Worker;

public interface IWorkerService extends ICrudService<Worker, String> {
	public Worker signUp(WorkerDto workerDto);
}