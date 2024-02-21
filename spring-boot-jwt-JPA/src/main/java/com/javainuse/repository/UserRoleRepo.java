package com.javainuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javainuse.model.UserRole;

public interface UserRoleRepo
    extends
    JpaRepository<UserRole, Integer>
{

    int countByRoleRoleId( int i );

    int countByRoleRoleIdAndUserEnabled( int i, boolean b );
    
}
