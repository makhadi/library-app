package com.npu.libraryapp.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import com.npu.libraryapp.domain.Book;
import com.npu.libraryapp.domain.Login;
import com.npu.libraryapp.domain.Student;
import com.npu.libraryapp.exceptions.BookDaoException;
import com.npu.libraryapp.services.BookService;
import com.npu.libraryapp.services.StudentService;

@Controller
public class BookController {
	
private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	@Qualifier("bookService")
	BookService bookService;
	
	@Autowired
	@Qualifier("studentService")
	StudentService studentService;
	
	@RequestMapping(value = {"addbook"}, method = RequestMethod.GET)
	public ModelAndView addBook(HttpSession session) {
		ModelAndView modelView;
		if(session.getAttribute("librarian") == null){
			modelView = new ModelAndView("login");
			modelView.addObject("login", new Login());
			modelView.addObject("timeoutmessage","Timeout, please login again.");
			return modelView;
		}
 		modelView = new ModelAndView("book/addBookForm");
 		modelView.addObject("book", new Book());
		return modelView;
	}
	
	@RequestMapping(value = {"processaddbookform"}, method = RequestMethod.POST)
	public ModelAndView processAddBookForm(@ModelAttribute("book") @Valid Book book, BindingResult result, HttpSession session) {
		ModelAndView modelView;
		if(session.getAttribute("librarian") == null){
			modelView = new ModelAndView("login");
			modelView.addObject("login", new Login());
			modelView.addObject("timeoutmessage","Timeout, please login again.");
			return modelView;
		}
		if (result.hasErrors()) {
			/*  Re-present the form with error messages */
			modelView = new ModelAndView("book/addBookForm");
			return modelView;
		}
		
		bookService.addNewBook(book);
 		modelView = new ModelAndView("book/bookAddedSuccessfully");
 		modelView.addObject("book", book);
		
		return modelView;
	}
	
	@RequestMapping(value = {"viewbooklist"}, method = RequestMethod.GET)
	public ModelAndView viewBookList(HttpSession session) {
		ModelAndView modelView;
		if(session.getAttribute("librarian") == null){
			modelView = new ModelAndView("login");
			modelView.addObject("login", new Login());
			modelView.addObject("timeoutmessage","Timeout, please login again.");
			return modelView;
		}
		
		List<Book> bookList = bookService.getBooks();
		
 		modelView = new ModelAndView("book/viewBookList");
 		modelView.addObject("books", bookList);
		return modelView;
	}
	
	@RequestMapping(value = {"viewissuebook"}, method = RequestMethod.POST, params = "issueBook")
	public ModelAndView viewIssueBook(String issuebookid,HttpSession session) {
		ModelAndView modelView;
		
		List<Student> studList;
		Book book=null;
		
		if(session.getAttribute("librarian") == null){
			modelView = new ModelAndView("login");
			modelView.addObject("login", new Login());
			modelView.addObject("timeoutmessage","Timeout, please login again.");
			return modelView;
		}
		
 		modelView = new ModelAndView("book/viewBookList");
			int selectedBook = Integer.parseInt(issuebookid);
			if(issuebookid != null){
				book = bookService.getBook(selectedBook);
			}
				studList = studentService.getStudents();
				
		modelView = new ModelAndView("book/viewIssueBook");
		modelView.addObject("issueBook", book);
		modelView.addObject("studentList", studList);
		return modelView;
	}
	
	@RequestMapping(value = {"viewissuedbooks"}, method = RequestMethod.GET)
	public ModelAndView viewIssuedBook(HttpSession session) {
		ModelAndView modelView;
		
		List<Map<String, Object>> booksIssued;
		if(session.getAttribute("librarian") == null){
			modelView = new ModelAndView("login");
			modelView.addObject("login", new Login());
			modelView.addObject("timeoutmessage","Timeout, please login again.");
			return modelView;
		}
		
		booksIssued = bookService.getIssuedBooks();
		modelView = new ModelAndView("book/viewIssuedBooks");
 		modelView.addObject("booksIssued", booksIssued);
		
		return modelView;
	}	
	
	@RequestMapping(value = {"issuebook"}, method = RequestMethod.POST)
	public ModelAndView issueBook(String bookId,String issueStudent, HttpSession session) {
		ModelAndView modelView;
		if(session.getAttribute("librarian") == null){
			modelView = new ModelAndView("login");
			modelView.addObject("login", new Login());
			modelView.addObject("timeoutmessage","Timeout, please login again.");
			return modelView;
		}
		
		modelView = new ModelAndView("book/viewIssueBook");
		int issueBookResult=0, bkId, studentId;
		bkId = Integer.parseInt(bookId);
		
		studentId = Integer.parseInt(issueStudent);
		try {
			issueBookResult = bookService.issueBook(bkId, studentId);
		} catch (BookDaoException e) {
			modelView = new ModelAndView("book/issueBookSuccess");
			modelView.addObject("issuewhilebookissue", e.getMessage());
			return modelView;
		}
		if(issueBookResult != -1){
			modelView = new ModelAndView("book/issueBookSuccess");
			modelView.addObject("bookissued", "Book Issued.");
		}
		return modelView;
		
	}
	
	@RequestMapping(value = {"viewissuebook"}, method = RequestMethod.POST, params = "editbook")
	public ModelAndView editBook(String issuebookid, HttpSession session) {
		ModelAndView modelView;
		
		if(session.getAttribute("librarian") == null){
			modelView = new ModelAndView("login");
			modelView.addObject("login", new Login());
			modelView.addObject("timeoutmessage","Timeout, please login again.");
			return modelView;
		}
		
		int bookId = Integer.parseInt(issuebookid);
		Book book = bookService.getBook(bookId);
		modelView = new ModelAndView("book/editBookForm");
		modelView.addObject("book", book);
		return modelView;
	}
	
	@RequestMapping(value = {"processeditbookform"}, method = RequestMethod.POST)
	public ModelAndView processEditBookForm(@ModelAttribute("book") @Valid Book book, BindingResult result, HttpSession session) {
		ModelAndView modelView;
		
		if(session.getAttribute("librarian") == null){
			modelView = new ModelAndView("login");
			modelView.addObject("login", new Login());
			modelView.addObject("timeoutmessage","Timeout, please login again.");
			return modelView;
		}
		
		if (result.hasErrors()) {
			/*  Re-present the form with error messages */
			modelView = new ModelAndView("book/editBookForm");
			return modelView;
		}
		
		bookService.updateBook(book);
 		modelView = new ModelAndView("book/bookUpdatedSuccessfully");
 		modelView.addObject("book", book);
		
		return modelView;
	}
	
	@RequestMapping(value = {"viewissuebook"}, method = RequestMethod.POST, params = "deletebook")
	public ModelAndView deleteBook(String issuebookid, HttpSession session) {
		ModelAndView modelView;
		
		if(session.getAttribute("librarian") == null){
			modelView = new ModelAndView("login");
			modelView.addObject("login", new Login());
			modelView.addObject("timeoutmessage","Timeout, please login again.");
			return modelView;
		}
		
		int bookId = Integer.parseInt(issuebookid);
		int result = bookService.deleteBook(bookId);
		if(result != 1){
			return new BookController().viewBookList(session);
		}
		modelView = new ModelAndView("book/bookDeleted");
		return modelView;
	}

}