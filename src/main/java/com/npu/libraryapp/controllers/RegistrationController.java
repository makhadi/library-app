package com.npu.libraryapp.controllers;

import java.util.Locale;

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
import com.npu.libraryapp.services.LibrarianService;

/**
 * Handles requests for the application home page.
 * Use URL:  http://localhost:8080/libraryapp/register
 */
@Controller
public class RegistrationController {
	
private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	@Qualifier("librarianService")
	LibrarianService librarianService;
	
	@RequestMapping(value = {"register"}, method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelView;
		
 		modelView = new ModelAndView("registration");
 		modelView.addObject("librarian", new Librarian());
		return modelView;
	}
	
	@RequestMapping(value = {"processregistrationform"}, method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(@ModelAttribute("librarian") @Valid Librarian librarian, BindingResult result) {
		ModelAndView modelView;
		
		if (result.hasErrors()) {
			/*  Re-present the form with error messages */
			modelView = new ModelAndView("registration");
			return modelView;
		}
		
		librarianService.addLibrarian(librarian);
 		modelView = new ModelAndView("registrationSuccess");
 		modelView.addObject("librarian", librarian);
		
		return modelView;
	}

}