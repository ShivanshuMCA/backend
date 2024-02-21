package com.javainuse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainuse.model.Token;
import com.javainuse.repository.TokenRepo;

@Service
public class TokenService {

	@Autowired
	private TokenRepo tokenRepo;

	public void saveToken(Token tokenDto) {

		tokenRepo.save(tokenDto);
	}

}
