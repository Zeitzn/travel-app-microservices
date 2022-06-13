package com.movi.user.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.movi.user.config.Auditable;

import lombok.Data;

@Data
//@Entity
@Document(value = "business_driver")
public class BusinessDriver extends Auditable implements Serializable {

	private static final long serialVersionUID = -1320151278450117102L;

	@Id
	private String id;

	/**
	 * Empresa
	 */
	private Business business;

	/**
	 * Conductor
	 */
	private User driver;
	
	private boolean enable;

	private boolean active;

}