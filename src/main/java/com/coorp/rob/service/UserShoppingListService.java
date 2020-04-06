package com.coorp.rob.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coorp.rob.dto.UserShoppingListDto;

@Service
public class UserShoppingListService {

	private static Logger log = LoggerFactory.getLogger(UserShoppingListService.class);
	
	@Autowired
	private ShopListService shopListService;
	
	@Autowired
	private UserService userService;
	
	public boolean saveShopListUser(UserShoppingListDto userShoppingListDto) {
		log.info("method  saveShopListUser(UserShoppingList userShoppingList)  - START\n\n" + 
				"\t PARAMS: {\n" + 
				"\t  1:" + userShoppingListDto.toString() +
				"\n\t}\n\n"); 
		boolean saved = true;

		
		return saved;
	}
	
}
