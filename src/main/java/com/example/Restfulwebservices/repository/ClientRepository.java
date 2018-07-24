package com.example.Restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Restfulwebservices.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	
	 @Query("select u from Client u where u.email = ?1")
	 public Client findByEmailAddress(String email);
}
