package com.npu.libraryapp.dao.jdbc;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.npu.libraryapp.dao.BookDAO;
import com.npu.libraryapp.domain.Book;
import com.npu.libraryapp.exceptions.BookDaoException;

@Repository("bookDaoJdbc")
public class BookDaoJdbcImpl implements BookDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private BookRowMapper bookRowMapper;
		
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		bookRowMapper = new BookRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("bookinfo")
		                 .usingGeneratedKeyColumns("bookId")
		                 .usingColumns("title","publisher","author","price","numOfBooks","lastUpdatedDate");
	}

	@Override
	public void addBook(Book book) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(book);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    book.setBookId(newId.intValue());
	}

	@Override
	public int updateBook(Book book) {
		String sql = "update bookinfo set title=:newTitle, publisher=:newPublisher, author=:newAuthor, price=:newPrice, "
				+ "numOfBooks=:newNumOfBooks where bookId=:bookId";
		int rowsAffected;
		int bookId = book.getBookId();
		MapSqlParameterSource params = new MapSqlParameterSource("bookId", bookId);
		params.addValue("newTitle", book.getTitle());
		params.addValue("newPublisher", book.getPublisher());
		params.addValue("newAuthor", book.getAuthor());
		params.addValue("newPrice", book.getPrice());
		params.addValue("newNumOfBooks", book.getNumOfBooks());

		rowsAffected = dbTemplate.update(sql, params);
		return rowsAffected;
	}

	@Override
	public int deleteBook(int bookId) {
		//String sql = "delete from bookinfo where bookId=:bookId";
		String sql = "update bookinfo set status = 'Inactive' where bookId=:bookId";
		int rowsAffected;
		MapSqlParameterSource params = new MapSqlParameterSource("bookId", bookId);
		rowsAffected = dbTemplate.update(sql, params);
		return rowsAffected;
	}

	@Override
	public int issueBook(int bookId, int studentId, Date dueDate) throws BookDaoException{
		String sql = "INSERT INTO bookissue(bookId, studentId, dueDate) "
				+ "Values(:bookId, :studentId, :dueDate)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		int numOfBooks = this.getNumOfBooks(bookId);
		int rowsAffected=0;
		if(numOfBooks > 0){
			params.addValue("bookId", bookId);
			params.addValue("studentId", studentId);
			params.addValue("dueDate", dueDate);
			try{
				rowsAffected = dbTemplate.update(sql, params);
			} catch(Exception e){
				throw new BookDaoException("Issue while issuing books.");
			}
			
		}
		
		if(rowsAffected != 1){
			throw new BookDaoException("Issue while issuing books.");
		}
		
		return rowsAffected;
	}

	@Override
	public List<Book> getBooksByTitle(String title) {
		String sql = "select * from bookinfo where title like :title and status='Active'";
		MapSqlParameterSource params = new MapSqlParameterSource("title",title);
		List<Book> books = dbTemplate.query(sql, params, bookRowMapper);
		return books;
	}

	@Override
	public List<Book> getBooks() {
		String sql = "select * from bookinfo where status='Active'";
		List<Book> books = dbTemplate.query(sql, bookRowMapper);
		return books;
	}

	@Override
	public List<Map<String, Object>> getIssuedBooks() {
		String sql = "select b.title,s.firstName,s.lastName,i.dateIssued,i.dueDate from "
				+ "bookinfo b join bookissue i on b.bookId=i.bookId join studentinfo s on i.studentId=s.studentId "
				+ "where b.status='Active'";
		List<Map<String, Object>> books = jdbcTemplate.queryForList(sql);
		return books;
	}

	@Override
	public Book getBook(int bookId) {
		String sql = "select * from bookinfo where bookId=:bookId and status='Active'";
		MapSqlParameterSource params = new MapSqlParameterSource("bookId",bookId); 
		Book book = dbTemplate.queryForObject(sql, params, bookRowMapper);
		return book;
	}

	@Override
	public int increamentNumOfBooks(int bookId, int count) {
		//yet to be implemented
		return 0;
	}

	@Override
	public int getNumOfBooks(int bookId) {
		String sql = "select numOfBooks from bookinfo where bookId = :bookId and status='Active'";
		int noOfBooks;
		MapSqlParameterSource params = new MapSqlParameterSource("bookId", bookId);
		noOfBooks = dbTemplate.queryForObject(sql, params, Integer.class);
		return noOfBooks;
	}

	@Override
	public int decreamentNumOfBooks(int bookId, int numOfBooks) throws BookDaoException{
		int updatedNumOfBooks;
		String sql = "update bookinfo set numOfBooks=:newNumOfBooks where bookId=:bookId and numOfBooks=:numOfBooks";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("bookId",bookId);
		params.addValue("newNumOfBooks", numOfBooks-1);
		params.addValue("numOfBooks", numOfBooks);
		updatedNumOfBooks = dbTemplate.update(sql, params);
		
		if(updatedNumOfBooks != 1){
			throw new BookDaoException("Issue while issuing books.");
		}
		
		return updatedNumOfBooks;
	}

}