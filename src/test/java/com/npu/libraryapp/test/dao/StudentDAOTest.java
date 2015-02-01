package com.npu.libraryapp.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

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

import com.npu.libraryapp.dao.StudentDAO;
import com.npu.libraryapp.domain.Student;

//@TransactionConfiguration
//@Transactional
@ContextConfiguration("classpath:dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentDAOTest {
	@Autowired
	@Qualifier("studentDaoJdbc")
	private StudentDAO studentDAO;
	private Student stud;
	private Logger logger = Logger.getLogger(StudentDAO.class);
	
	@Before
	public void init(){
		stud = new Student("Bob", "Wilson", "408-408-4084");
		stud.setStudentId(1);
	}
	
	//Testing if able to add a student to the database
	@Test
	public void testAddStudent() {
		Student stud = new Student("Steve", "Balmer", "408-408-4085");
		studentDAO.addStudent(stud);
		assertTrue(stud.getStudentId() > 0);
	}
	
	//Testing if able to update a student to the database
	@Test
	public void testUpdateStudent(){
		Student stud = new Student("Greg", "Monzo", "408-408-4086");
		studentDAO.addStudent(stud);
		
		Student addedStudent = studentDAO.getStudentById(stud.getStudentId());
		addedStudent.setFirstName("Gregory");
		studentDAO.updateStudent(addedStudent);
		
		assertEquals(addedStudent.getFirstName(),"Gregory");
	}
	
	//Testing if able to delete a student to the database
	@Test
	public void testDeleteStudent(){
		Student stud = new Student("Greg", "Monzo", "408-408-4086");
		studentDAO.addStudent(stud);
		
		int rowsAffected = studentDAO.deleteStudent(stud.getStudentId());
		assertTrue(rowsAffected == 1);
	}
	
	//Testing if able to get list of all students from database
	@Test
	public void testGetStudents(){
		List<Student> students = studentDAO.getStudents();
		logger.info("Size of number of students available in database: "+students.size());
		assertTrue(students.size() == 2);
	}
	
	//Testing if able to get list of students from the database when some name is passed
	@Test
	public void testGetStudentsByName(){
		List<Student> students = studentDAO.getStudentsByName("Bob","Wilson");
		logger.info("Size of number of students available in database: "+students.size());
		assertTrue(students.size() == 1);
	}
	
	//Testing if able to get a student from the database when some id is passed
	@Test
	public void testGetStudentById(){
		Student student = studentDAO.getStudentById(stud.getStudentId());
		assertNotNull(student);
	}

}
