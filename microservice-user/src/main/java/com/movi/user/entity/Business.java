package com.movi.user.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.movi.user.config.Auditable;

import lombok.Data;

@Data
//@Entity
@Document(value = "business")
public class Business extends Auditable implements Serializable {

	private static final long serialVersionUID = -1320151278450117102L;

	@Id
	private String id;

	/**
	 * Código de la empresa
	 */
	private String code;

	/**
	 * Nombre de la empresa
	 */
	private String name;

	/**
	 * Correo electrónico gerencial de la empresa
	 */
	private String email;

	/**
	 * Número de conductores que puede dar de alta la empresa
	 */
	private Integer driversNumber;

}