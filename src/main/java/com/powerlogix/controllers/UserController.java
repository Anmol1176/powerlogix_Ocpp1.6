package com.powerlogix.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController 
{
		
	@RequestMapping("/dashboard")
	public String User(Model model, Principal principal) 
	{
		model.addAttribute("title", "Dashboard Station power logix application");

		return "normal/websocket";
	}
		
}
