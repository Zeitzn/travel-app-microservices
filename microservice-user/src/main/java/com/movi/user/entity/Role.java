package com.movi.user.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.movi.user.config.Auditable;

import lombok.Data;

@Data
//@Entity
@Document(value = "role")
public class Role extends Auditable implements Serializable {

	private static final long serialVersionUID = -2339332863069832745L;

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	//@Column(name = "name", length = 25, nullable = false)
	private String name;

}
