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
public class UpdateProfileController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	@Qualifier("librarianService")
	LibrarianService librarianService;
	
	@RequestMapping(value = {"updateprofile"}, method = RequestMethod.GET)
	public ModelAndView updateProfile(HttpSession session) {
		ModelAndView modelView;
		Librarian librarian = (Librarian)session.getAttribute("librarian");
		if(librarian == null){
			modelView = new ModelAndView("login");
			modelView.addObject("login", new Login());
			modelView.addObject("timeoutmessage","Timeout, please login again.");
			return modelView;
		}
			modelView = new ModelAndView("updateProfileForm");
			modelView.addObject("librarian",librarian);

		return modelView;
	}
	
	@RequestMapping(value = {"processupdateprofileform"}, method = RequestMethod.POST)
	public ModelAndView updateProfileProcess(@ModelAttribute("librarian") @Valid Librarian librarian, BindingResult result, HttpSession session) {
			ModelAndView modelView;
			if(session.getAttribute("librarian") == null){
				modelView = new ModelAndView("login");
				modelView.addObject("login", new Login());
				modelView.addObject("timeoutmessage","Timeout, please login again.");
				return modelView;
			}
			librarianService.updateLibrarian(librarian);
			modelView = new ModelAndView("updateProfileSuccess");
			session.setAttribute("librarian",librarian);
		return modelView;
	}

}