package com.security.controller;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/")
	public String redirectToAccueil() {
		
		return "redirect:/accueil";
	}
	
	@GetMapping("/accueil")
	public String accueil(@AuthenticationPrincipal User user, Model model) {
		
			model.addAttribute("user", user);
			
		
		return "index";
		
	}
	
	@GetMapping("/profile")
	public String profile(@AuthenticationPrincipal User user, Model model) {
		
		if(user != null) {
			model.addAttribute("user", user);
			return "administration/profile";
			
		}else {
			return "redirect:/accueil";
		}

	}
	
	//1ère méthode accès admin avec le rôle Admin
	@GetMapping("/administration")
	public String administration(@AuthenticationPrincipal User user, Model model) {
		
		if(user !=null && user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
			
			return "administration/administration";
		}else {
			return "administration/error";
		}
		
	}
	
	/*
	 * //2e méthode
	 * 
	 * public String administration() {
	 * 
	 * return "administration/administration"; }
	 */

}
