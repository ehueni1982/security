package com.security.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.model.DBuser;
import com.security.repository.DBuserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private DBuserRepository dbUserBuserRepository;
	
	
	//A chaque fois qu'un utilisateur essayera de se connecter cette méthode sera appellée
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Permet de récupérer un utilisateur
		DBuser user = dbUserBuserRepository.findByUsername(username);
		
		return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRole()));
		
	}
	
	
	private List<GrantedAuthority> getGrantedAuthorities(String role){
		
		//GrandtedAuthority est l'objet d'autorisation
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		
		//Cette ligne permet d'ajouter des autorisations à la liste Authorities
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		
		return authorities;
	}

}
