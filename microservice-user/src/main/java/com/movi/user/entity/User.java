package com.movi.user.entity;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.movi.user.config.Auditable;

import lombok.Data;

@Data
//@Entity
//@Table(name = "users", uniqueConstraints = { //@UniqueConstraint(columnNames = { "username" }),
		//@UniqueConstraint(columnNames = { "email" }) })

@Document(value = "users")
public class User extends Auditable implements Serializable {

	private static final long serialVersionUID = 4958660484115553189L;

	@Id
	private String id;

	private String imageUrl;
	
	private String imageId;
	
	private String code;
	
	private String firstName;

	/**
	 * Apellidos
	 */
	private String lastName;

	/**
	 * Nombre de usuario
	 */
	private String username;

	/**
	 * Correo electrónico
	 */
	private String email;

	/**
	 * Contraseña
	 */
	private String password;

	/**
	 * Roles de usuario
	 */
	private Set<Role> roles;

	/**
	 * En caso de ser conductor indica si esta disponible para tomar viajes
	 */
	private boolean available;
	
	/**
	 * Indica que el usuario puede hacer uso de la aplicación
	 */
	private boolean enable;
	
	/**
	 * Indica que el usuario a activado el acceso a la aplicación desde el enlace de confirmación
	 */
	private boolean active;

}