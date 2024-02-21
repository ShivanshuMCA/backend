package com.javainuse.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javainuse.model.Authority;
import com.javainuse.model.LoginUser;
import com.javainuse.model.Role;
import com.javainuse.model.UserDTO;
import com.javainuse.model.UserRole;
import com.javainuse.repository.RoleRepo;
import com.javainuse.repository.UserDao;
import com.javainuse.repository.UserRoleRepo;

@Service
public class JwtUserDetailsService
    implements
    UserDetailsService
{
    @Autowired
    private UserDao         userDao;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private RoleRepo        roleRepo;
    @Autowired
    private UserRoleRepo    userRoleRepo;
    @Override
    public UserDetails loadUserByUsername( String username )
        throws UsernameNotFoundException
    {
        LoginUser user = userDao.findByUsername( username );
        if ( user == null )
        {
            throw new UsernameNotFoundException( "User not found with username: " + username );
        }
        return new org.springframework.security.core.userdetails.User( user.getUsername(),
                                                                       user.getPassword(),
                                                                       new ArrayList<>() );
    }

    public LoginUser save( UserDTO user )
    {
        LoginUser newUser = new LoginUser();
        Role role = new Role();
        UserRole userRole = new UserRole();
        LoginUser savedUser = null;
        BeanUtils.copyProperties( user, newUser );
        newUser.setPassword( bcryptEncoder.encode( user.getPassword() ) );
        newUser.setEnabled( true );
        savedUser = userDao.save( newUser );
        if ( !savedUser.equals( null ) )
        {
            role = roleRepo.findByRoleName( user.getUserType() );
            userRole.setUser( savedUser );
            userRole.setRole( role );
            userRoleRepo.save( userRole );
        }
        return savedUser;
    }

    public LoginUser findUserByUsername( String username )
    {
        LoginUser user = userDao.findByUsername( username );
        if ( user != null )
        {
            List<Authority> auth = new ArrayList<>();
            user.getUserRoles().forEach( userRole -> {
                Authority au = new Authority();
                au.setAuthority( userRole.getRole().getRoleName() );
                auth.add( au );
            } );
            user.setAuthorities( auth );
        }
        return user;
    }

    public int countUserByUserType()
    {
        return userRoleRepo.countByRoleRoleId( 1 );
    }

    public int countUserByUserTypeAndStatus()
    {
        return userRoleRepo.countByRoleRoleIdAndUserEnabled( 1, true );
    }

    public LoginUser update( @Valid LoginUser user )
    {
        LoginUser savedUser = null;
        savedUser = userDao.save( user );
        savedUser.setAuthorities( user.getAuthorities() );
        System.out.println( savedUser.toString() );
        return savedUser;
    }

    public boolean verifyUsername( @Valid String username )
    {
        return userDao.existsByUsername(username);
    }
}