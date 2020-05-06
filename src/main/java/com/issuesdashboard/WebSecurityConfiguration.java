package com.issuesdashboard;

import javax.websocket.Endpoint;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.StaticResourceLocation;
import org.springframework.boot.autoconfigure.security.servlet.StaticResourceRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		return new InMemoryUserDetailsManager(
				User.withDefaultPasswordEncoder().username("user").password("password").authorities("ROLE_USER").build(),
				User.withDefaultPasswordEncoder().username("admin").password("admin").authorities("ROLE_ACTUTOR" , "ROLE_USER","ROLE_USER").build());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.requestMatchers(EndpointRequest.to("info")).permitAll()
		.requestMatchers(EndpointRequest.toAnyEndpoint()).hasAnyRole("ACTUTOR")
		.antMatchers("admin").hasAnyRole("ADMIN")
		.antMatchers("/**").permitAll()
		.and().httpBasic();
	}
}
