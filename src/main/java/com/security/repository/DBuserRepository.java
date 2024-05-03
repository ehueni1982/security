package com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.model.DBuser;

public interface DBuserRepository extends JpaRepository<DBuser, Integer>{
	
	//Recherche via username
	public DBuser findByUsername(String username);

}

