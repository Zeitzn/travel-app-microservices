package com.movi.user.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.movi.user.config.Auditable;

import lombok.Data;

@Data
//@Entity
@Document(value = "business_vehicle")
public class BusinessVehicle extends Auditable implements Serializable {

	private static final long serialVersionUID = -1320151278450117102L;

	@Id
	//@GeneratedValue(generator = "uuid2")
	//@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;

	/**
	 * Empresa
	 */
	//@OneToOne
	//@JoinColumn(name = "business_id", nullable = false, foreignKey = //@ForeignKey(name = "FK_business_vehicle_business_id"))
	private Business business;

	/**
	 * Vehículo
	 */
	//@OneToOne
	//@JoinColumn(name = "vehicle_id", nullable = false, foreignKey = //@ForeignKey(name = "FK_business_vehicle_vehicle_id"))
	private Vehicle vehicle;

}