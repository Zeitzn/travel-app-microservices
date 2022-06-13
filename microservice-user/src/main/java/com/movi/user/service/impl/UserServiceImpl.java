package com.movi.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.movi.user.commons.CrudImpl;
import com.movi.user.commons.IGenericDAO;
import com.movi.user.dao.IUserDao;
import com.movi.user.entity.User;
import com.movi.user.service.IUserService;

@Service
public class UserServiceImpl extends CrudImpl<User, String> implements UserDetailsService, IUserService {
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private IUserDao dao;

	@Override
	protected IGenericDAO<User, String> dao() {
		return dao;
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = findByUsernameOrEmail(username,username);
		String password = null;
		
		if (user == null) {
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '" + username + "' en el sistema!");
		}
		password = user.getPassword();

		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(autority -> logger.info("Role: ".concat(autority.getAuthority()))).collect(Collectors.toList());

		System.out.println(user);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), password,
				user.isActive()
						&& user.isEnable(),
				true, true, true, authorities);
	}
	
	@Override
	public boolean existsByUsername(String username) {
		return dao.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return dao.existsByEmail(email);
	}

	@Override
	public User findByUsernameOrEmail(String username, String email) {
		return dao.findByUsernameOrEmail(username, email);
	}

}