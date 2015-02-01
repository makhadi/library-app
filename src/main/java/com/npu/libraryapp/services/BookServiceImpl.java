package com.npu.libraryapp.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.npu.libraryapp.dao.BookDAO;
import com.npu.libraryapp.domain.Book;
import com.npu.libraryapp.exceptions.BookDaoException;

@Service("bookService")
@Transactional(readOnly=true)
public class BookServiceImpl implements BookService{
	@Autowired
	@Qualifier("bookDaoJdbc")
	private BookDAO bookDao;
	
	@Override
	@Transactional(readOnly=false)
	public Book addNewBook(Book book) {
		//Book newBook;
		
		//newBook = new Book(book);
		bookDao.addBook(book);
		
		return book;
	}
	
	@Override
	@Transactional(readOnly=false)
	public Book updateBook(Book book){
		bookDao.updateBook(book);
		
		return book;
	}
	
	@Transactional(readOnly=false)
	public int deleteBook(int bookId){
		return bookDao.deleteBook(bookId);
	}
	
	public List<Book> getBooksByTitle(String title){
		return bookDao.getBooksByTitle(title);
	}
	
	public List<Book> getBooks(){
		return bookDao.getBooks();
	}
	
	public List<Map<String, Object>> getIssuedBooks() {
		return bookDao.getIssuedBooks();
	}
	
	public Book getBook(int bookId) {
		return bookDao.getBook(bookId);
	}
	
	public int getNumOfBooks(int bookId) {
		return bookDao.getNumOfBooks(bookId);
	}
	
	@Transactional(readOnly=false)
	public int decreamentNumOfBooks(int bookId, int numOfBooks) throws BookDaoException{
		return bookDao.decreamentNumOfBooks(bookId, numOfBooks);
	}
	
	public Date getDueDate() {
		GregorianCalendar gCal = new GregorianCalendar();
		int day, dayOfWeek;
		Date date = new Date(System.currentTimeMillis());
		gCal.setTime(date);
		dayOfWeek = gCal.DAY_OF_WEEK;
		day = gCal.get(dayOfWeek);
		if(day == Calendar.WEDNESDAY || day == Calendar.THURSDAY || day == Calendar.FRIDAY){
			gCal.add(dayOfWeek, 5);
		} else if(day == Calendar.SATURDAY){
			gCal.add(dayOfWeek, 4);
		} else {
			gCal.add(dayOfWeek, 3);
		}
		date = (Date) gCal.getTime();
		return date;
	}

	@Override
	@Transactional(readOnly=false, rollbackForClassName="BookDaoException")
	public int issueBook(int bookId, int studentId) throws BookDaoException {
		int numOfBooks = bookDao.getNumOfBooks(bookId);
		int result1 = bookDao.issueBook(bookId, studentId, getDueDate());
		int result2 = bookDao.decreamentNumOfBooks(bookId, numOfBooks);
		if(result1 != 1 || result2 != 1){
			throw new BookDaoException("Issue while issuing books.");
		}
		return 1;
	}
	
}