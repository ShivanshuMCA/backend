package com.javainuse.controller;

import java.security.Principal;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.model.JwtRequest;
import com.javainuse.model.JwtResponse;
import com.javainuse.model.LoginUser;
import com.javainuse.model.Token;
import com.javainuse.model.UserDTO;
import com.javainuse.service.JwtUserDetailsService;
import com.javainuse.service.TokenService;

@RestController @CrossOrigin(origins = "*", allowedHeaders = "*")
public class JwtAuthenticationController
{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil          jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private TokenService          tokenService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken( @RequestBody JwtRequest authenticationRequest )
        throws Exception
    {
        authenticate( authenticationRequest.getUsername(), authenticationRequest.getPassword() );
        final UserDetails userDetails = userDetailsService.loadUserByUsername( authenticationRequest.getUsername() );
        final String token = jwtTokenUtil.generateToken( userDetails );
        LoginUser user = userDetailsService.findUserByUsername( authenticationRequest.getUsername() );
        Token tokenDto = new Token();
        tokenDto.setToken( token );
        tokenDto.setUser( user );
        tokenDto.setCreatedDate( new Date() );
        tokenService.saveToken( tokenDto );
        return ResponseEntity.ok( new JwtResponse( token ) );
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser( @Valid @RequestBody UserDTO user )
        throws Exception
    {
        LoginUser SavedUser = null;
        LoginUser userExist = userDetailsService.findUserByUsername( user.getUsername() );
        if ( userExist == null )
        {
            SavedUser = userDetailsService.save( user );
        }
        else
        {
            System.out.println( "Username is Already Exist" );
            throw new Exception( "Username is Already Exist" );
        }
        return ResponseEntity.ok( SavedUser );
    }

    private void authenticate( String username, String password )
        throws Exception
    {
        try
        {
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( username, password ) );
        }
        catch ( DisabledException e )
        {
            throw new Exception( "USER_DISABLED", e );
        }
        catch ( BadCredentialsException e )
        {
            throw new Exception( "INVALID_CREDENTIALS", e );
        }
    }

    @GetMapping(value = "/{username}")
    public LoginUser getUser( @PathVariable("username") String username )
    {
        return this.userDetailsService.findUserByUsername( username );
    }

    @GetMapping(value = "/current-user")
    public LoginUser getCurrentUser( Principal pl )
    {
        return this.userDetailsService.findUserByUsername( pl.getName() );
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@Valid @RequestBody LoginUser user) throws Exception {
        LoginUser SavedUser = null;
            SavedUser = userDetailsService.update(user);

        return ResponseEntity.ok(SavedUser);
    }
    
    @GetMapping(value = "verifyUserName/{username}")
    public boolean verifyUser(@Valid @PathVariable("username") String username )
    {
        
        return this.userDetailsService.verifyUsername( username );
    }
}