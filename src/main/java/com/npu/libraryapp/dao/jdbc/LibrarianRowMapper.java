package com.npu.libraryapp.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.npu.libraryapp.domain.Librarian;

public class LibrarianRowMapper implements RowMapper<Librarian> {

	public Librarian mapRow(ResultSet resultSet, int row) throws SQLException {
		
		String firstName, lastName, emailId, password;
		
		Librarian librarian;
		
		firstName = resultSet.getString("firstName");
		lastName = resultSet.getString("lastName");
		emailId = resultSet.getString("emailId");
		password = resultSet.getString("password");
		
		librarian = new Librarian(firstName, lastName, emailId, password);
		
		librarian.setLibrarianId(resultSet.getInt("librarianId"));
		return librarian;
	}

}
