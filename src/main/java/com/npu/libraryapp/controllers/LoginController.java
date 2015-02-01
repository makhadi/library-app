package com.npu.libraryapp.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.npu.libraryapp.domain.Librarian;
import com.npu.libraryapp.domain.Login;
import com.npu.libraryapp.services.LibrarianService;

/**
 * Handles requests for the application home page.
 * Use URL:  http://localhost:8080/libraryapp/login
 */
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	@Qualifier("librarianService")
	LibrarianService librarianService;
	
	@RequestMapping(value = {"login"}, method = RequestMethod.GET)
	public ModelAndView login(HttpSession session) {
		if(session.getAttribute("librarian") != null){
			return new LoginController().libraryHomeGet(session);
		}
		ModelAndView modelView;
		
 		modelView = new ModelAndView("login");
 		modelView.addObject("login", new Login());
		return modelView;
	}
	
	@RequestMapping(value = {"libraryhome"}, method = RequestMethod.POST)
	public ModelAndView libraryHome(@ModelAttribute("login") @Valid Login login, BindingResult result, HttpSession session) {
		ModelAndView modelView;
		Librarian librarian;
		
		int librarianId = librarianService.checkLibrarian(login.getEmailId(), login.getPassword());
		if (result.hasErrors()) {
			/*  Re-present the form with error messages */
			modelView = new ModelAndView("login");
			return modelView;
		}
		
		librarian = librarianService.getLibrarian(librarianId);
		if(librarian == null){
			modelView = new ModelAndView("login");
			modelView.addObject("incorrectlogin","Incorrect login details.");
			return modelView;
		}
		modelView = new ModelAndView("libraryHome");
		session.setAttribute("librarian",librarian);
		modelView.addObject("librarian",librarian);
 		
		return modelView;
	}
	
	@RequestMapping(value = {"libraryhome"}, method = RequestMethod.GET)
	public ModelAndView libraryHomeGet(HttpSession session) {
		ModelAndView modelView;
		Librarian librarian;
		librarian = (Librarian) session.getAttribute("librarian");
		if(librarian == null){
			modelView = new ModelAndView("login");
			modelView.addObject("timeoutmessage","Timeout, please login again.");
			modelView.addObject("login", new Login());
			return modelView;
		}
		modelView = new ModelAndView("libraryHome");
		modelView.addObject("librarian",librarian);
 		
		return modelView;
	}
	
	@RequestMapping(value = {"logout"}, method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		ModelAndView modelView;
		modelView = new ModelAndView("home");
		modelView.addObject("logoutmessage","You have been successfully logged out.");
		session.removeAttribute("librarian");
 		
		return modelView;
	}
	
}