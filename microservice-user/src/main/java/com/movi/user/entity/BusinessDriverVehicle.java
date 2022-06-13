package com.movi.user.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.movi.user.config.Auditable;

import lombok.Data;

@Data
//@Entity
@Document(value = "business_driver_vehicle")
public class BusinessDriverVehicle extends Auditable implements Serializable {

	private static final long serialVersionUID = -1320151278450117102L;

	@Id
	private String id;

	/**
	 * Conductor de la empresa
	 */
	private BusinessDriver businessDriver;

	/**
	 * Vehiculo de la empresa
	 */
	private BusinessVehicle businessVehicle;

	private boolean enable;

	private boolean active;

}