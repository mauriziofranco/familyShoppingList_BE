package com.coorp.rob;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.coorp.rob.model.Item;
import com.coorp.rob.model.ShopList;
import com.coorp.rob.repository.ShopListRepository;

@SpringBootApplication
public class Application {

	@Autowired
	private ShopListRepository shopListRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	 @Bean
	 public CommandLineRunner runner() {
		 return (args) ->{
			 Item item1 = new Item();
			 item1.setName("latte");
			 
			 Item item2 = new Item();
			 item1.setName("uova");
			 
			 List<Item> list = new ArrayList();
			 list.add(item1);
			 list.add(item2);
			 
			 ShopList shopList = new ShopList();
			 shopList.setListIem(list);
			 
			 shopListRepo.save(shopList);
		 };
	 }
}
