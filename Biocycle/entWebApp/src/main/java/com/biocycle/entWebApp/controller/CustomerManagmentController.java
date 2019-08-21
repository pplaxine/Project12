package com.biocycle.entWebApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerManagmentController {
	
	@RequestMapping("/cms/organisations")
	public String home(Model model, HttpSession session) {
		return "organisationsValidations";
	}
}
