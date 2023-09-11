package com.powerlogix.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.powerlogix.models.Role;
import com.powerlogix.models.User;
import com.powerlogix.models.UserRole;
import com.powerlogix.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController 
{	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
		
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String indexPage(Model model) 
	{
		model.addAttribute("title", "Login power logix application");
		return "index";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register power logix application");
		model.addAttribute("user", new User());
		return "signup";
	}
	
	// handler for registering user
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user , Model model,HttpSession session)
	{
		try {
			
			System.out.println("USER" + user);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
           Set<UserRole> roles = new HashSet<>();
			
			Role role = new Role();
			role.setRoleId(45L);
			role.setRoleName("ROLE_USER");
			
			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRole.setRole(role);

			roles.add(userRole);
			User result = this.userService.saveUser(user,roles);

			model.addAttribute("user", new User());
			
			return "redirect:/";
			
			

		} catch (Exception e) {
			e.printStackTrace();
			return "signup";
		}
		

	}
}

