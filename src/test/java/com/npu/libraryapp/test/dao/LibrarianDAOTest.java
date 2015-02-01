package com.npu.libraryapp.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.npu.libraryapp.dao.LibrarianDAO;
import com.npu.libraryapp.domain.Book;
import com.npu.libraryapp.domain.Librarian;
import com.npu.libraryapp.services.LibrarianService;

//@TransactionConfiguration
//@Transactional
@ContextConfiguration("classpath:dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class LibrarianDAOTest {
	
	@Autowired
	@Qualifier("librarianDaoJdbc")
	private LibrarianDAO librarianDAO;
	private Librarian librarian;
	private Logger logger = Logger.getLogger(LibrarianDAO.class);
	@Autowired
	@Qualifier("librarianService")
	LibrarianService librarianService;
	
	@Before
	public void init(){
		librarian = new Librarian("John","Smith","john@test.com","fremont");
		librarian.setLibrarianId(1);
	}
	
	//Testing if able to add a librarian to the database
	@Test
	public void testAddLibrarian(){
		Librarian librarian = new Librarian("library","Service","service@test.com","florida");
		librarianService.addLibrarian(librarian);
		assertTrue(librarian.getLibrarianId() > 0);
	}
	
	/*//Testing if able to update a librarian to the database
	@Test
	public void testUpdateLibrarian(){
		Librarian librarian = new Librarian("Bob","Walter","alter@test.com","florida");
		librarianDAO.addLibrarian(librarian);
		Librarian addedLibr = librarianDAO.getLibrarian(librarian.getLibrarianId());
		addedLibr.setEmailId("walter@test.com");
		librarianDAO.updateLibrarian(librarian);
		
		assertEquals(addedLibr.getEmailId(),"walter@test.com");
	}
	
	//Testing if able to delete a librarian to the database
	@Test
	public void testDeleteLibrarian(){
		Librarian librarian = new Librarian("Bobby","Walters","walters@test.com","florida");
		librarianDAO.addLibrarian(librarian);
		
		int rowsAffected = librarianDAO.deleteLibrarian(librarian.getLibrarianId());
		assertTrue(rowsAffected == 1);
	}
	
	//Testing if some librarian exist in the database
	@Test
	public void testCheckLibrarian(){
		int librarianId = librarianDAO.checkLibrarian("john@test.com", "fremont");
		assertTrue(librarianId == 1);
	}
	
	//Testing if able to get a librarian from the database
	@Test
	public void testGetLibrarian(){
		Librarian libr = librarianDAO.getLibrarian(librarian.getLibrarianId());
		assertNotNull(libr);
	}*/
	
}