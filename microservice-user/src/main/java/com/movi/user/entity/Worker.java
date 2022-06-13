package com.movi.user.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.movi.user.config.Auditable;

import lombok.Data;

@Data
@Document(value = "worker")
public class Worker extends Auditable implements Serializable {

	private static final long serialVersionUID = -1320151278450117102L;

	@Id
	private String id;

	/**
	 * Nombres del trabajador
	 */
	private String firstName;

	/**
	 * Apellidos del trabajador
	 */
	private String lastName;

	private User user;

	private Business business;

}