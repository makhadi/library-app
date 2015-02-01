package com.npu.libraryapp.services;

import java.util.List;

import com.npu.libraryapp.domain.Student;

public interface StudentService {
	public Student addStudent(Student stud);
	public Student udpateStudent(Student stud);
	public int deleteStudent(int studentId);
	public List<Student> getStudents();
	public List<Student> getStudentsByName(String firstName, String lastName);
	public Student getStudentById(int studentId);
}
