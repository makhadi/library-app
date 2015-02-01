package com.npu.libraryapp.dao.jdbc;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.npu.libraryapp.dao.LibrarianDAO;
import com.npu.libraryapp.domain.Book;
import com.npu.libraryapp.domain.Librarian;

@Repository("librarianDaoJdbc")
public class LibrarianDaoJdbcImpl implements LibrarianDAO {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private LibrarianRowMapper librarianRowMapper;
		
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		librarianRowMapper = new LibrarianRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("librarianinfo")
		                 .usingGeneratedKeyColumns("librarianId")
		                 .usingColumns("firstName", "lastName", "emailId", "password");
	}
	
	@Override
	public void addLibrarian(Librarian libr) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(libr);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    libr.setLibrarianId(newId.intValue());
	}

	@Override
	public int updateLibrarian(Librarian libr) {
		String sql = "Update librarianinfo set firstName=:firstName, lastName=:lastName, "
				+ "emailId=:emailId, password=:password where librarianId=:librarianId";
		int rowsAffected;
		int librarianId = libr.getLibrarianId();
		System.out.println(libr);
		MapSqlParameterSource params = new MapSqlParameterSource("librarianId", librarianId);
		params.addValue("firstName", libr.getFirstName());
		params.addValue("lastName", libr.getLastName());
		params.addValue("emailId", libr.getEmailId());
		params.addValue("password", libr.getPassword());

		rowsAffected = dbTemplate.update(sql, params);
		return rowsAffected;
	}

	@Override
	public int deleteLibrarian(int librarianId) {
		//String sql = "delete from librarianinfo where librarianId=:librarianId";
		String sql = "update librarianinfo set status = 'Inactive' where librarianId=:librarianId";
		int rowsAffected;
		MapSqlParameterSource params = new MapSqlParameterSource("librarianId", librarianId);
		rowsAffected = dbTemplate.update(sql, params);
		return rowsAffected;
	}

	@Override
	public int checkLibrarian(String emailId, String password) {
		String sql = "select librarianId from librarianinfo where emailId = :emailId and password = :password and status = 'Active'";
		int librarianId;
		MapSqlParameterSource params = new MapSqlParameterSource("emailId", emailId);
		params.addValue("password", password);
		try{
			librarianId = dbTemplate.queryForObject(sql, params, Integer.class);
		}catch (EmptyResultDataAccessException e) {
			return -1;
		}
		return librarianId;
	}

	@Override
	public Librarian getLibrarian(int librarianId) {
		String sql = "select * from librarianinfo where librarianId=:librarianId and status='Active'";
		MapSqlParameterSource params = new MapSqlParameterSource("librarianId",librarianId);
		Librarian librarian=null;
		try{
			librarian = dbTemplate.queryForObject(sql, params, librarianRowMapper);
			librarian.setLibrarianId(librarianId);
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
		return librarian;
	}

}