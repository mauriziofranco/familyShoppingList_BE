package com.coorp.rob.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coorp.rob.dto.ResponseMessage;
import com.coorp.rob.dto.UserShoppingListDto;
import com.coorp.rob.model.ShopList;
import com.coorp.rob.service.ItemService;
import com.coorp.rob.service.ShopListService;

/**
 * @author Roberto
 * 
 * */
@RestController
@RequestMapping("/api/shop-list")
public class ShopListRestController {

	private static Logger log = LoggerFactory.getLogger(ShopListRestController.class);

	@Autowired
	private ShopListService shopListService;
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 
	 * */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<ShopList>> getShoppingList(){
		log.info("getShoppingList - START"); 
		List<ShopList> list = this.shopListService.getShoppingList();
		
		ResponseEntity<List<ShopList>> responseToFrontEnd = null;
//		if(!shopList.isPresent()) 
//			responseToFrontEnd = new ResponseEntity<ShopList>(HttpStatus.NOT_FOUND);
//		
//		else 
			responseToFrontEnd = new ResponseEntity<List<ShopList>>(list, HttpStatus.OK);
		
		log.info("method getShopList(@PathVariable String id) - END");
		return responseToFrontEnd;
	}
	
	/**
	 * 
	 * */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ShopList> getShopList(@PathVariable String id){
		log.info("method  getShopList(@PathVariable String id) - START\n\n" + 
				  "\t PARAMS: {\n" + 
				  					"\t  1:    "+  "("+ id.getClass().getSimpleName() +")"+ id +
				  			  "\n\t}\n\n"); 
		
		Optional<ShopList> shopList = this.shopListService.getShopListById(id);
		
		ResponseEntity<ShopList> responseToFrontEnd = null;
		if(!shopList.isPresent()) 
			responseToFrontEnd = new ResponseEntity<ShopList>(HttpStatus.NOT_FOUND);
		
		else 
			responseToFrontEnd = new ResponseEntity<ShopList>(shopList.get(), HttpStatus.OK);
		
		log.info("method getShopList(@PathVariable String id) - END");
		return responseToFrontEnd;
	}
	
	/**
	 * 
	 * */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> saveShopList(@RequestBody ShopList shopList){
		log.info("method save(@RequestBody UserShoppingList userShoppingList) - START\n\n" + 
				  "\t PARAMS: {\n" + 
				  					"\t  1:    " + shopList.toString() +
				  			  "\n\t}\n\n"); 
		ResponseEntity<ResponseMessage> responseToFrontEnd = null;
		ResponseMessage respMessage = new ResponseMessage();
		boolean savedSucces = this.shopListService.saveShopList(shopList);
		log.error("method save(@RequestBody ShopList shopList) - DEBUG\n\n" + 
				  "\t {\n" + 
				  		"\t  saved-success: " + savedSucces +
				  "\n\t}\n\n");
		if(savedSucces) {
			respMessage.setMessage("shop-list create succesfull");
			responseToFrontEnd = new ResponseEntity<ResponseMessage>(respMessage,HttpStatus.CREATED);
		}
		else {
			respMessage.setMessage("shop-list not created");
			responseToFrontEnd = new ResponseEntity<ResponseMessage>(respMessage,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("method save(@RequestBody UserShoppingList userShoppingList) - END"); 
		return responseToFrontEnd;
	}
	

	/**
	 * 
	 * */
	@RequestMapping(value = "/save/items", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> saveItemInShopList(@RequestBody UserShoppingListDto useShoppingListDto){
		log.info("method saveItemInShopList(@RequestBody UserShoppingList userShoppingList) - START\n\n" + 
				  "\t PARAMS: {\n" + 
				  					"\t  1:   " + useShoppingListDto.getEmail() + ", " + useShoppingListDto.getItems()[0] + 
				  			  "\n\t}\n\n"); 
		ResponseEntity<ResponseMessage> responseToFrontEnd = null;
		ResponseMessage respMessage = new ResponseMessage();
		
		// retrieved shoplist and put items
		boolean saved = this.itemService.saveItemInShopList(useShoppingListDto);
		if( saved ) {
			respMessage.setMessage("item saved succesfull");
			responseToFrontEnd = new ResponseEntity<ResponseMessage>(HttpStatus.OK);
		}
		else {
			respMessage.setMessage("item  not saved ");
			responseToFrontEnd = new ResponseEntity<ResponseMessage>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("method saveItemInShopList(@RequestBody UserShoppingList userShoppingList) - END"); 
		return responseToFrontEnd;
	}

}
