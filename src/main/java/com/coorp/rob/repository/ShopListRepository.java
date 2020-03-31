package com.coorp.rob.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.coorp.rob.model.ShopList;

@Repository
public interface ShopListRepository extends MongoRepository<ShopList, String>{
	
	  public Optional<ShopList> findById(String id);
	  	  
}
