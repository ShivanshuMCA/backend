package com.javainuse.repository;

import javax.validation.Valid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javainuse.model.LoginUser;

@Repository
public interface UserDao extends CrudRepository<LoginUser, Integer> {
	
	LoginUser findByUsername(String username);

    boolean existsByUsername( @Valid String username );
	
}