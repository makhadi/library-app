package com.npu.libraryapp.test.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.npu.libraryapp.dao.BookDAO;
import com.npu.libraryapp.domain.Book;
import com.npu.libraryapp.domain.Student;
import com.npu.libraryapp.exceptions.BookDaoException;
import com.npu.libraryapp.services.BookService;
import com.npu.libraryapp.services.BookServiceImpl;

//@TransactionConfiguration
//@Transactional
@ContextConfiguration("classpath:dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BookDAOTest {
	@Autowired
	@Qualifier("bookDaoJdbc")
	private BookDAO bookDAO;
	private Book book;
	private Logger logger = Logger.getLogger(BookDAO.class);
	
	@Before
	public void init(){
		book = new Book("Advanced Java","Pearson","Author",99,15);
		book.setBookId(1);
	}

	//Testing if able to add a book to the database
	@Test
	public void testAddBook() {
		Book book = new Book("REST","McGraw","Bert",88,12);
		bookDAO.addBook(book);
		assertTrue(book.getBookId() > 0);
	}
	
	//Testing if able to update a book to the database
	@Test
	public void testUpdateBook(){
		Book book = new Book("WebServices","McGraw","Bates",100,10);
		bookDAO.addBook(book);
		Book addedBook = bookDAO.getBook(book.getBookId());
		addedBook.setTitle("WebServicesUpdated");
		bookDAO.updateBook(addedBook);
		
		assertEquals(addedBook.getTitle(),"WebServicesUpdated");
	}
	
	//Testing if able to delete a book to the database
	@Test
	public void testDeleteBook(){
		Book book = new Book("WebServices","McGraw","Bates",100,10);
		bookDAO.addBook(book);
		
		int rowsAffected = bookDAO.deleteBook(book.getBookId());
		assertTrue(rowsAffected == 1);
	}
	
	//Testing if issuing a book to a student works fine
	//Incorrect numOfBooks is being passed so that the whole transaction gets rolls back
	@Test
	@Transactional(readOnly=false, rollbackForClassName="BookDaoException")
	public void testIssueBook(){
		Book book = new Book("WebServices2","McGraw Hill","Saira",100,10);
		bookDAO.addBook(book);
		int bookId = book.getBookId();
		
		Student stud = new Student("Bob","Wilson","408-408-4084");
		stud.setStudentId(1);
		int studentId = stud.getStudentId();
		
		int numOfBooks = bookDAO.getNumOfBooks(bookId);
		try {
			bookDAO.issueBook(bookId, studentId, new Date(System.currentTimeMillis()));
			bookDAO.decreamentNumOfBooks(bookId, 87); 
		} catch (BookDaoException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Testing if are able to get list of books when title is passed
	@Test
	public void testGetBooksByTitle(){
		List<Book> books = bookDAO.getBooksByTitle("Advanced Java");
		logger.info("Size of number of books available in database: "+books.size());
		assertTrue(books.size() == 1);
	}
	
	//Testing if we are able to get list of books from database
	@Test
	public void testGetBooks(){
		List<Book> books = bookDAO.getBooks();
		logger.info("Size of number of books available in database: "+books.size());
		assertTrue(books.size() == 1);
	}
	
	//Testing to get number of books from database
	@Test
	public void testGetNumberOfBooks(){
		int bookId = book.getBookId();
		int numOfBooks = bookDAO.getNumOfBooks(bookId);
		logger.info("For bookd id:  "+bookId+" Number of books available in database: "+numOfBooks);
		assertTrue(numOfBooks == 15);
	}

}