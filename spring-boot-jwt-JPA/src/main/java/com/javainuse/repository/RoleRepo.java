package com.javainuse.repository;

import org.springframework.data.repository.CrudRepository;

import com.javainuse.model.Role;

public interface RoleRepo extends CrudRepository<Role, Integer>{

	Role findByRoleName(String roleName);
	Role findByRoleId(Integer id);

}
