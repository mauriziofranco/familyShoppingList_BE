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
import com.coorp.rob.repository.ShopListRepository;
import com.coorp.rob.repository.UserRepository;

@SpringBootApplication
public class Application {

	@Autowired
	private ShopListRepository shopListRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	 @Bean
	 public CommandLineRunner runner() {
		 return (args) ->{
			 Item item1 = new Item();
			 item1.setName("latte");
			 item1.setTaken(false);
			 
			 Item item2 = new Item();
			 item2.setName("uova");
			 item2.setTaken(true);
			 
			 List<Item> list = new ArrayList();
			 list.add(item1);
			 list.add(item2);
			 
			 ShopList shopList = new ShopList();
			 shopList.setId("axxxx1123");
			 shopList.setItemList(list);

			 shopListRepo.save(shopList);
			 
			 UserInfo userInfo = new UserInfo();
			 userInfo.setName("Roberto");
			 userInfo.setSurname("Amato");
			 userInfo.setCitta("Licata");
			 userInfo.setIdListItem("xivvx21");
			 userInfo.setCap("92027");
			 
			 User user = new User();
			 user.setUserEmail("robertoxx93@libero.it");
			 user.setUserInfo(userInfo);
		
			 userRepository.save(user);
		 };
	 }
}
