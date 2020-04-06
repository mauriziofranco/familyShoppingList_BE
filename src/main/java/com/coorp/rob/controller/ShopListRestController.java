package com.coorp.rob.controller;

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
import com.coorp.rob.model.ShopList;
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
	
	
	/**
	 * 
	 * */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ShopList> getShopList(@PathVariable String id){
		log.info("method  getShopList(@PathVariable String id) - START\n\n" + 
				  "\t PARAMS: {\n" + 
				  					"\t  1:" + id +
				  			  "\n\t}\n\n"); 
		
		Optional<ShopList> shopList = this.shopListService.getShopListById(id);
		log.error("method getShopList(@PathVariable String id) - DEBUG\n\n" + 
				  "\t {\n" + 
				  		"\t  shopList: " + shopList.isPresent() != null?shopList.get().toString():"null" + 
				  "\n\t}\n\n");
		ResponseEntity<ShopList> responseToFrontEnd = null;
		if(!shopList.isPresent()) {
			responseToFrontEnd = new ResponseEntity<ShopList>(shopList.get(),HttpStatus.NOT_FOUND);
		}
		else {
			responseToFrontEnd = new ResponseEntity<ShopList>(shopList.get(), HttpStatus.OK);
		}
		log.info("method getShopList(@PathVariable String id) - RETURN" +
				 "\t {\n" + 
				 	"\t  responseEntity-headers:" + responseToFrontEnd.getHeaders().toString() +
				 	"\t  responseEntity-statuCode:" + responseToFrontEnd.getStatusCode().toString() +				 	
					"\t  responseEntity-body:" + responseToFrontEnd.getBody().toString() +
			     "\n\t}\n\n"); 
		log.info("method getShopList(@PathVariable String id) - END");
		return responseToFrontEnd;
	}
	
	/**
	 * 
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> saveShopList(@RequestBody ShopList shopList){
		log.info("method save(@RequestBody UserShoppingList userShoppingList) - START\n\n" + 
				  "\t PARAMS: {\n" + 
				  					"\t  1:" + shopList.toString() +
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
		
		log.info("method getShopList(@PathVariable String id) - RETURN" +
				 "\t {\n" + 
				 	"\t  responseEntity-headers:" + responseToFrontEnd.getHeaders().toString() +
				 	"\t  responseEntity-statuCode:" + responseToFrontEnd.getStatusCode().toString() +				 	
					"\t  responseEntity-body:" + responseToFrontEnd.getBody().toString() +
			    "\n\t}\n\n"); 
		log.info("method save(@RequestBody UserShoppingList userShoppingList) - END"); 
		return responseToFrontEnd;
	}

}
