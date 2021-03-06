package com.movi.user.config;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Class containing important fields for auditing
 * 
 * @author Isac Huamán Pineda
 * @version 1.0
 * 
 * @see <a href = "http://https://www.mivolco.com/" /> </a>
 *
 */
@SuperBuilder
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public abstract class Auditable {

	/**
	 * Campo para guardar el Usuario Creado por
	 * @author Isac Huamán Pineda
	 */
	@CreatedBy
	protected String createdBy;

	/**
	 * Campo para guardar la fecha y hora de creación
	 * @author Isac Huamán Pineda
	 */
	@CreatedDate
	protected Date createdDate;

	/**
	 * Campo para guardar el usuario que realizó la última actualización
	 * @author Isac Huamán Pineda
	 */
	@LastModifiedBy
	protected String lastModifiedBy;

	/**
	 * Campo para guardar la fecha y hora de ultima actualización
	 * @author Isac Huamán Pineda
	 */
	@LastModifiedDate
	protected Date lastModifiedDate;

}
