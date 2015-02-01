package com.npu.libraryapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.npu.libraryapp.dao.StudentDAO;
import com.npu.libraryapp.domain.Student;

@Service("studentService")
@Transactional(readOnly=true)
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	@Qualifier("studentDaoJdbc")
	StudentDAO studentDAO;
	
	@Override
	@Transactional(readOnly=false)
	public Student addStudent(Student stud){
		studentDAO.addStudent(stud);
		return stud;
	}
	
	@Override
	@Transactional(readOnly=false)
	public Student udpateStudent(Student stud){
		studentDAO.updateStudent(stud);
		return stud;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int deleteStudent(int studentId){
		return studentDAO.deleteStudent(studentId);
	}
	
	@Override
	public Student getStudentById(int studentId){
		return studentDAO.getStudentById(studentId);
	}
	
	@Override
	public List<Student> getStudentsByName(String firstName, String lastName) {
		return studentDAO.getStudentsByName(firstName, lastName);
	}
	
	@Override
	public List<Student> getStudents() {
		return studentDAO.getStudents();
	}

}