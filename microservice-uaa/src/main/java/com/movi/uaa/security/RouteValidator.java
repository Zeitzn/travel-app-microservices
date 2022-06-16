package com.movi.uaa.security;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.movi.uaa.dto.RequestDto;

@Component
@ConfigurationProperties(prefix="admin-paths")//Get the prefix value from .yaml
public class RouteValidator {
	
	private List<RequestDto> paths;//must be equal to the child of admin-paths

	public List<RequestDto> getPaths() {
		return paths;
	}

	public void setPaths(List<RequestDto> paths) {
		this.paths = paths;
	}
	
	public boolean isAdminPath(RequestDto dto) {
		return paths.stream().anyMatch(p -> Pattern.matches(p.getUri(), dto.getUri()) && p.getMethod().equals(dto.getMethod()));
	}
}
