package com.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Gestion de l'authorisation et l'authentification
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	
	//Gestion de la sécurité http
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(authorizeRequests ->
		
		authorizeRequests.requestMatchers("/admin").hasRole("ADMIN")
		.requestMatchers("/user").hasRole("USER")
		//.requestMatchers("/administration").hasRole("ADMIN")
		//Pas besoin d'authentifier accueil
		.requestMatchers("/accueil").permitAll()
		.anyRequest().authenticated()
			).formLogin(Customizer.withDefaults())
			
			 .formLogin(formLogin -> {
				
				formLogin.defaultSuccessUrl("/profile");
			
				//Redirection vers la page d'accueil
		}).logout( logout -> logout.logoutSuccessUrl("/accueil"));
		
		return http.build();
	}
	
	//hashage de mot de passe sécurisé
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
		
	}

}
