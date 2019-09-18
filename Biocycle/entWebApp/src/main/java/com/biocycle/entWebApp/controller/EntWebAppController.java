package com.biocycle.entWebApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class EntWebAppController.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Controller
public class EntWebAppController {
	
	/**
	 * Home.
	 *
	 * @param model the model
	 * @param session the session
	 * @return the string
	 */
	@RequestMapping("/")
	public String home(Model model, HttpSession session) {
		return "home";
	}
}
