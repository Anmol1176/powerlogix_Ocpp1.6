package com.powerlogix.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController 
{
	@RequestMapping("/dashboard")
	public String dashboard(Model model, Principal principal) 
	{
		model.addAttribute("title", "Dashboard Station power logix application");

		return "admin/dashboard";
	}
}
