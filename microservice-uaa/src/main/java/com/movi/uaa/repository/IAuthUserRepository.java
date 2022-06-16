package com.movi.uaa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movi.uaa.entity.AuthUser;

public interface IAuthUserRepository extends JpaRepository<AuthUser, String> {
	
	Optional<AuthUser> findByUsername(String username);

}
