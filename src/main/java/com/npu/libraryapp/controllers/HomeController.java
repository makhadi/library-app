package com.npu.libraryapp.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 * Use URL:  http://localhost:8080/libraryapp   OR    http://localhost:8080/libraryapp/home
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
		if(session.getAttribute("librarian") != null){
			return new LoginController().libraryHomeGet(session);
		}

		return new ModelAndView("home");
	}
	
}