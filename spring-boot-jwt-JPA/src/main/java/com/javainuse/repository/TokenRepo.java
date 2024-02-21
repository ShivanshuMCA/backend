package com.javainuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javainuse.model.Token;

@Repository
public interface TokenRepo extends JpaRepository<Token, Integer> {

}
