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
import com.coorp.rob.model.User;
import com.coorp.rob.service.UserService;

/**
 * @author Roberto
 * 
 * */
@RestController
@RequestMapping("/api/user")
public class UserRestController {

	private static Logger log = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * */
	@RequestMapping(value = "/{email}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable String email){
		log.info("method  getShopList(@PathVariable String id) - START\n\n" + 
				  "\t PARAMS: {\n" + 
				  					"\t  1:" + email +
				  			  "\n\t}\n\n"); 
		
		ResponseEntity<User> responseToFrontEnd = null;
		Optional<User> user = this.userService.getUserByEmail(email);
		if(!user.isPresent()) {
			responseToFrontEnd = new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		else {
			responseToFrontEnd = new ResponseEntity<User>(user.get(), HttpStatus.OK);
		}
		log.info("method getShopList(@PathVariable String id) - END");
		return responseToFrontEnd;
	}
	
	/**
	 * 
	 * */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> saveUser(@RequestBody User user){
		log.info("method save(@RequestBody User user) - START\n\n" + 
				  "\t PARAMS: {\n" + 
				  					"\t  1:" + user.toString() +
				  			  "\n\t}\n\n");
		ResponseEntity<ResponseMessage> responseToFrontEnd = null;
		ResponseMessage respMessage = new ResponseMessage();
		boolean savedSucces = this.userService.saveUser(user);
		log.error("method save(@RequestBody User user) - DEBUG\n\n" + 
				  "\t {\n" + 
				  		"\t  saved-success: " + savedSucces +
				  "\n\t}\n\n");
		if(savedSucces) {
			respMessage.setMessage("user create with success");
			responseToFrontEnd = new ResponseEntity<ResponseMessage>(respMessage, HttpStatus.CREATED);
		}
		else {
			responseToFrontEnd = new ResponseEntity<ResponseMessage>(respMessage, HttpStatus.INTERNAL_SERVER_ERROR);
			respMessage.setMessage("user not created");

		}
		
		log.info("method getShopList(@RequestBody User user) - RETURN" +
				 "\t {\n" + 
				 	"\t  responseEntity-headers:" + responseToFrontEnd.getHeaders().toString() +
				 	"\t  responseEntity-statuCode:" + responseToFrontEnd.getStatusCode().toString() +				 	
					"\t  responseEntity-body:" + responseToFrontEnd.getBody().toString() +
			    "\n\t}\n\n"); 
		
		log.info("method save(@RequestBody User user) - END"); 
		return responseToFrontEnd;
	}
	
	/**
	 * 
	 * */
	@RequestMapping(value = "{email}/shop-list/", method = RequestMethod.GET)
	public ResponseEntity<ShopList> getShopListUser(@PathVariable String email){
		log.info("method getShopListUser(@PathVariable String email) - START\n\n" + 
				  "\t PARAMS: {\n" + 
				  					"\t  1:" + email +
				  			  "\n\t}\n\n");
		ResponseEntity<ShopList> responseToFrontEnd = null;
		ResponseMessage respMessage = new ResponseMessage();
		Optional<ShopList> shopList = this.userService.getShopListUser(email);
		
		if(null != shopList) {
			responseToFrontEnd = new ResponseEntity<ShopList>(shopList.get(), HttpStatus.OK);
		}
		else {
			responseToFrontEnd = new ResponseEntity<ShopList>(HttpStatus.NO_CONTENT);
			respMessage.setMessage("user not created");
		}
		
		
		log.info("method getShopListUser(@PathVariable String email) - END"); 
		return responseToFrontEnd;
	}
}