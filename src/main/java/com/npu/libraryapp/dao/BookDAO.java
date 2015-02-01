package com.npu.libraryapp.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.npu.libraryapp.domain.Book;
import com.npu.libraryapp.exceptions.BookDaoException;

public interface BookDAO {
	public void addBook(Book book);
	public int updateBook(Book book);
	public int deleteBook(int bookId);
	public int getNumOfBooks(int bookId);
	public int decreamentNumOfBooks(int bookId, int numOfBooks) throws BookDaoException;
	public int issueBook(int bookId, int studentId, Date issueDate) throws BookDaoException;
	public List<Book> getBooksByTitle(String title);
	public List<Book> getBooks();
	public List<Map<String, Object>> getIssuedBooks();
	public Book getBook(int bookId);
	public int increamentNumOfBooks(int bookId, int count);
}
