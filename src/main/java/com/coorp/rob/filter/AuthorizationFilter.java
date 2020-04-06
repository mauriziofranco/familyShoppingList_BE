package com.coorp.rob.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.coorp.rob.utility.JwtProvider;

/**
 * @author Roberto
 * 
 * */
public class AuthorizationFilter extends BasicAuthenticationFilter {
	
	private static Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);
	
	private JwtProvider jwtProvider;
	
    private String prefix;
    
    private String param;
	
	
	/**
	 * constructor
	 * 
	 * @param authenticationManager - 
	 * */
	@Autowired
    public AuthorizationFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider, String prefix, String param) {
		super(authenticationManager);		// call constructor of parent
		log.info("constructor AuthorizationFilter(AuthenticationManager authenticationManager) - START");
		this.jwtProvider = jwtProvider;
	    this.prefix = prefix;
	    this.param = param;
		log.info("constructor AuthorizationFilter(AuthenticationManager authenticationManager) - END");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("method doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) - START");
		log.info("method doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) - PARAMS: client-request-header-Authorization = " + request.getHeader(this.param) + ", server-response-status = " + response.getStatus());
	
		String jwtRequestRetrieved = null;
		jwtRequestRetrieved = request.getHeader(this.param); 	// retrieve value of attribute 'Authorization' of client request
		log.info("method doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) - DEBUG: jwtRequestRetrieved = " + jwtRequestRetrieved); 

	
		// if jwt not prensent in the request
		if(jwtRequestRetrieved == null || !jwtRequestRetrieved.startsWith(this.prefix)) {
			chain.doFilter(request, response);	
			return ;
		}
		else {
			UsernamePasswordAuthenticationToken authentication = this.getAuthentication(jwtRequestRetrieved);
			log.info("method doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) - DEBUG: authentication = " + authentication); 
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        chain.doFilter(request, response);
		}
		
		log.info("method doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) - END");
	}
	
	
	/**
	 * 
	 * @param String jwt
	 * @return UsernamePasswordAuthenticationToken 
	 *
	 * */
	private UsernamePasswordAuthenticationToken getAuthentication(String jwt) {
		log.info("method getAuthenticationToken(String jwt) - START");
		DecodedJWT decodedJwt = this.jwtProvider.decodeJwt(jwt);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(decodedJwt.getSubject(), 
																									 null,
																									 Collections.emptyList());
		
		log.info("method getAuthenticationToken(String jwt) - DEBUG: authentication = " + authentication.getPrincipal()); 

		log.info("method getAuthenticationToken(String jwt) - END");
		log.info("method generateJwt() - RETURNED: authentication =" + authentication);
		return authentication;
	}
}
