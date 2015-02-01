package com.npu.libraryapp.resthandlers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.npu.libraryapp.domain.Student;
import com.npu.libraryapp.exceptions.UnknownResourceException;
import com.npu.libraryapp.services.StudentService;

@Path("/")
public class StudentRestHandler {
	@Autowired
	private StudentService studentService;
	private Logger logger = Logger.getLogger(StudentRestHandler.class);
	
	/* Look up a Student from the student id.   
	 * Matching Url:
	 * http://localhost:8080/libraryapp/webservices/student/{id}   Note -- replace {id} with a number (the id of the student)
	 * See web.xml file for Jersey configuration
	 */
	@GET
	@Path("/student/{id}")
	@Produces("application/xml, application/json")
	public Student getStudent(@PathParam("id") int id) {
		Student student = null;
		
		try {
			student = studentService.getStudentById(id);
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
		
		if (student == null) {
			logger.debug("Failed Request to lookup student with id  : " + id);
			throw new UnknownResourceException("Student id: " + id + " is invalid");
		}
		
		return student;
	}
	
	/* Return a list of all the students
	 * Matching Url:
	 * http://localhost:8080/libiraryapp/webservices/student
	 * See web.xml file for Jersey configuration
	 */
	@GET
	@Path("/student")
	@Produces("application/xml")
	public List<Student> getStudents() {
		List<Student> studList;
		
		studList = studentService.getStudents();
		return studList;
	}
	
	
	/* Create a new Student
	 * URL:  http://localhost:8080/libraryapp/webservices/student
	 * To test, you can POST the data from file student.xml or student.json (see resources folder):
	 * After doing the post, use a get command to retrieve the student (and verify that the post was successful).
	 */
	@POST
	@Path("/student")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public Response addStudent(Student newStudent) {
		ResponseBuilder respBuilder;
		
		try { 
			studentService.addStudent(newStudent);
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
		
		logger.debug("Successfully created a new Student: " + newStudent);
		respBuilder = Response.status(Status.CREATED);
		respBuilder.entity(newStudent);
		return respBuilder.build();
	}
	
}