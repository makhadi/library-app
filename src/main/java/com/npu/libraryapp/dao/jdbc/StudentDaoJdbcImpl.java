package com.npu.libraryapp.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.npu.libraryapp.dao.StudentDAO;
import com.npu.libraryapp.domain.Book;
import com.npu.libraryapp.domain.Student;

@Repository("studentDaoJdbc")
public class StudentDaoJdbcImpl implements StudentDAO{
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private StudentRowMapper studentRowMapper;
		
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		studentRowMapper = new StudentRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("studentinfo")
		                 .usingGeneratedKeyColumns("studentId")
		                 .usingColumns("firstName", "lastName", "phoneNumber");
	}

	@Override
	public void addStudent(Student stud) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(stud);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    stud.setStudentId(newId.intValue());
	}

	@Override
	public int updateStudent(Student stud) {
		String sql = "UPDATE studentinfo set firstName=:firstName, lastName=:lastName, "
				+ "phoneNumber=:phoneNumber where studentId=:studentId ";
		int rowsAffected;
		int studentId = stud.getStudentId();
		MapSqlParameterSource params = new MapSqlParameterSource("studentId", studentId);
		params.addValue("firstName", stud.getFirstName());
		params.addValue("lastName", stud.getLastName());
		params.addValue("phoneNumber", stud.getPhoneNumber());

		rowsAffected = dbTemplate.update(sql, params);
		return rowsAffected;
	}

	@Override
	public int deleteStudent(int studentId) {
		//String sql = "delete from studentinfo where studentId=:studentId";
		String sql = "update studentinfo set status = 'Inactive' where studentId=:studentId";
		int rowsAffected;
		MapSqlParameterSource params = new MapSqlParameterSource("studentId", studentId);
		rowsAffected = dbTemplate.update(sql, params);
		return rowsAffected;
	}

	@Override
	public List<Student> getStudents() {
		String sql = "select * from studentinfo where status='Active'";
		List<Student> students = dbTemplate.query(sql, studentRowMapper);
		return students;
	}

	@Override
	public List<Student> getStudentsByName(String firstName, String lastName) {
		String sql = "select * from studentinfo where firstName like :firstName and lastName like :lastName and status='Active'";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("firstName", firstName);
		params.addValue("lastName", lastName);
		List<Student> students = dbTemplate.query(sql, params, studentRowMapper);
		return students;
	}

	@Override
	public Student getStudentById(int studentId) {
		String sql = "select * from studentinfo where studentId=:studentId and status='Active'";
		MapSqlParameterSource params = new MapSqlParameterSource("studentId",studentId); 
		Student stud = dbTemplate.queryForObject(sql, params, studentRowMapper);
		return stud;
	}

}