package com.movi.user.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * AuditorAwareImpl.java Configuration class to get the authenticated user and
 * handle it in audits
 * 
 * @author Isasc Huamán Pineda
 * @version 1.0
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}

		return Optional.of(authentication.getName());
	}

}
