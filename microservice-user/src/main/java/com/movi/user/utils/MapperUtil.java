package com.movi.user.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperUtil<E, D> {

	@Autowired
	private ModelMapper mapper;

	@SuppressWarnings("unchecked")
	private E convertToEntity(D dto) {
		return (E) mapper.map(dto, Object.class);
	}

	@SuppressWarnings("unchecked")
	private D convertToDto(E entity) {
		return (D) mapper.map(entity, Object.class);
	}

	public List<D> convertToDtoList(List<E> listEntity) {
		return listEntity.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<E> convertToEntityList(List<D> listDto) {
		return listDto.stream().map(this::convertToEntity).collect(Collectors.toList());
	}
}
