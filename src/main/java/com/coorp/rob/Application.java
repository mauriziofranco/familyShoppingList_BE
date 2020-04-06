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
import com.coorp.rob.model.User;
import com.coorp.rob.model.UserInfo;
import com.coorp.rob.service.ShopListService;
import com.coorp.rob.service.UserService;

@SpringBootApplication
public class Application {

	@Autowired
	private ShopListService shopListService;
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	 @Bean
	 public CommandLineRunner runner() {
		 return (args) ->{
			 Item item1 = new Item();
			 item1.setItemName("miele");
			 item1.setItemTaken(false);
			 
			 Item item2 = new Item();
			 item2.setItemName("banane");
			 item2.setItemTaken(true);
			 
			 List<Item> list = new ArrayList<Item>();
			 list.add(item1);
			 list.add(item2);
			 
			 String idList = "ayyyt1123";
			 ShopList shopList = new ShopList();
			 shopList.setId(idList);
			 shopList.setItemList(list);

			 shopListService.saveShopList(shopList);
			 
			 UserInfo userInfo = new UserInfo();
			 userInfo.setName("Paolo");
			 userInfo.setSurname("Pilla");
			 userInfo.setCitta("Torino");
			 userInfo.setIdListItem(idList);
			 userInfo.setCap("90089");
			 
			 String userEmail = "apila@ymail.it";
			 String password = "1234";
			 User user = new User();
			 user.setUserEmail(userEmail);
			 user.setPassword(password);
			 user.setUserInfo(userInfo);
		
			 userService.saveUser(user);
			 
		 };
	 }
}
