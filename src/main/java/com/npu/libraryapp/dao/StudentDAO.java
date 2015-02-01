package com.npu.libraryapp.dao;

import java.util.List;

import com.npu.libraryapp.domain.Student;

public interface StudentDAO {
	public void addStudent(Student stud);
	public int updateStudent(Student stud);
	public int deleteStudent(int studentId);
	public List<Student> getStudents();
	public List<Student> getStudentsByName(String firstName, String lastName);
	public Student getStudentById(int studentId);
}
