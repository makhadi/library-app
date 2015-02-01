package com.npu.libraryapp.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.npu.libraryapp.domain.Book;

public class BookRowMapper implements RowMapper<Book> {

	public Book mapRow(ResultSet resultSet, int row) throws SQLException {
		
		String title, publisher, author;
		double price;
		int numOfBooks;
		
		Book book;
		
		title = resultSet.getString("title");
		publisher = resultSet.getString("publisher");
		author = resultSet.getString("author");
		price = resultSet.getDouble("price");
		numOfBooks = resultSet.getInt("numOfBooks");
		
		book = new Book(title, publisher, author, price, numOfBooks);
		
		book.setBookId(resultSet.getInt("bookId"));
		return book;
	}

}