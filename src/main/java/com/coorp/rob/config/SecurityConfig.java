package com.coorp.rob.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.coorp.rob.filter.AuthorizationFilter;
import com.coorp.rob.utility.JwtProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static Logger log = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private JwtProvider jwtProvider;
	
    @Value("${security.prefix}")
    private String prefix;
    
    @Value("${security.param}")
    private String param;
    
	@Value("${api.authorized}")
	private String API_AUTHORIZED_WITHOUT_JWT;
	

	/**
	 * 
	 * */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("method configure(HttpSecurity http) - START");
		log.info("method configure(HttpSecurity http) - PARAM: http = " + http);
		log.info("method configure(HttpSecurity http) - DEBUG: jwtProvider = " + this.jwtProvider);
		
		
		// allow to disable cors 
		http
			.cors().and().csrf()
								.disable();
		
		// allow to authorize request for specific API
		http
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilter( new AuthorizationFilter(this.authenticationManager(), jwtProvider, prefix, param) )
			.authorizeRequests()
			.antMatchers(this.API_AUTHORIZED_WITHOUT_JWT).permitAll()
																	 .anyRequest().authenticated();
		
		log.info("method configure(HttpSecurity http) - END");

	}
	
}
