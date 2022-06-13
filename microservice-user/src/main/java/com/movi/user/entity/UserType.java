package com.movi.user.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.movi.user.config.Auditable;

import lombok.Data;

@Data
//@Entity
@Document(value = "ctype_user")
public class UserType extends Auditable implements Serializable {

	private static final long serialVersionUID = 4751493784126516215L;

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	//@Column(name = "name", length = 50)
	private String name;
}
