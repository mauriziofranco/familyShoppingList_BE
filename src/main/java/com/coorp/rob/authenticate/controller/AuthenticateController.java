package com.coorp.rob.authenticate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coorp.rob.dto.AuthenticationInputDto;
import com.coorp.rob.dto.AuthenticationOutputDto;
import com.coorp.rob.model.User;
import com.coorp.rob.service.UserService;
import com.coorp.rob.utility.JwtProvider;


@RestController
@RequestMapping(value = "/public/authentication")
public class AuthenticateController {
	
	private static Logger log = LoggerFactory.getLogger(AuthenticateController.class);
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UserService userService;
	
	/**
	 * API  ---> '/authenticate' 
	 * 
	 * */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody AuthenticationInputDto body) {
		log.info("method login(@RequestBody LoginInputDto body) - START");
		log.info("method login(@RequestBody LoginInputDto body) - PARAM: body = { " + body.getEmail() + ", " + body.getPassword() + " }");
		
		ResponseEntity<?> responseEntity = null;
		User user = this.userService.getUserByEmailAndPassword(body.getEmail(), body.getPassword());
		if(user==null) {
			responseEntity = ResponseEntity.badRequest().build();
			log.info("method login(@RequestBody LoginInputDto body) - DEBUG: responseEntity = " + responseEntity.getStatusCode()); 

		}
		else {
			String jwt = this.jwtProvider.generateJwt();
			log.info("method login(@RequestBody LoginInputDto body) - DEBUG: jwtGenerated = " + jwt); 

			AuthenticationOutputDto authenticationOutputDto = new AuthenticationOutputDto();
			authenticationOutputDto.setJwt(jwt);
			log.info("method login(@RequestBody LoginInputDto body) - DEBUG: loginOutputDto.getJwt() = " + authenticationOutputDto.getJwt()); 

			responseEntity = ResponseEntity.ok(authenticationOutputDto);
			log.info("method login(@RequestBody LoginInputDto body) - DEBUG: responseEntity = " + responseEntity.getStatusCode()); 

		}
		
		log.info("method login(@RequestBody LoginInputDto body) - RETURNED: ");
		log.info("method login(@RequestBody LoginInputDto body) - END");
		return responseEntity;
	}

}
