package com.npu.libraryapp.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.npu.libraryapp.domain.Librarian;
import com.npu.libraryapp.domain.Student;

import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper<Student> {
	
public Student mapRow(ResultSet resultSet, int row) throws SQLException {
		
		String firstName, lastName, phoneNumber;
		
		Student stud;
		
		firstName = resultSet.getString("firstName");
		lastName = resultSet.getString("lastName");
		phoneNumber = resultSet.getString("phoneNumber");
		
		stud = new Student(firstName, lastName, phoneNumber);
		
		stud.setStudentId(resultSet.getInt("studentId"));
		
		return stud;
	}

}
