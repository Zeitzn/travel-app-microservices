package com.movi.user.commons;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericDAO<T, ID> extends MongoRepository<T, ID> {

}