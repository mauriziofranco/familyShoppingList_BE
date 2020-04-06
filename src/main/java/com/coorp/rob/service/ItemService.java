package com.coorp.rob.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coorp.rob.dto.UserShoppingListDto;
import com.coorp.rob.model.Item;
import com.coorp.rob.model.ShopList;
import com.coorp.rob.model.User;


/**
 * @author Roberto
 * 
 * */
@Service
public class ItemService {
	
	private static Logger log = LoggerFactory.getLogger(ItemService.class);

	@Autowired
	private ShopListService shopListService;	

	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * 
	 * */
	public boolean saveItemInShopList(UserShoppingListDto userShoppingListDto) {
		log.info("method saveItem(UserShoppingListDto userShoppingListDto) - START" +
							   "\t PARAMS: {\n" + 
											"\t  1:    " + userShoppingListDto.getEmail() + ", " + userShoppingListDto.getItems()[0] +
									  "\n\t}\n\n"); 

		boolean saveOrUpdateList = false;
		boolean added = false;
		String idList = null;
		Optional<User> user = this.userService.getUserByEmail(userShoppingListDto.getEmail());
		if(user.isPresent()) {
			idList = user.get().getUserInfo().getIdListItem();
			
			Optional<ShopList> shopList = this.shopListService.getShopListById(idList);
			if(shopList.isPresent()) {
				List<Item> listItemToAdd = Arrays.asList(userShoppingListDto.getItems());
			    added = shopList.get().getItemList().addAll(listItemToAdd);
			    System.out.println(" shopListUpdated = " + shopList.get().getItemList());
				shopList.get().setItemList(shopList.get().getItemList());
				
				// update
				saveOrUpdateList = this.shopListService.saveShopList(shopList.get());
			}
		}
		
		log.info("method saveItem(UserShoppingListDto userShoppingListDto) - END");
		return (added && saveOrUpdateList);
	}
}
