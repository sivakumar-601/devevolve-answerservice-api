package com.devevolve.answerservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(authorize -> authorize
	                .requestMatchers("/public/**", "/error").permitAll()
	                .anyRequest().authenticated()
	            )
	            .oauth2Login(); // enables Authorization Code flow with configured providers

	        return http.build();
	    }
}
