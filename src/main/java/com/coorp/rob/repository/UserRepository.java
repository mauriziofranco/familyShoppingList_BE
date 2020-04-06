package com.coorp.rob.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.coorp.rob.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	public Optional<User> findByUserEmail(String userEmail);	

	public Optional<User> findByUserEmailAndPassword(String userEmail, String password);	
}
