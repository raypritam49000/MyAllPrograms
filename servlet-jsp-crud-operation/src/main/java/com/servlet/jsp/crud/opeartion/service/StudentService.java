package com.servlet.jsp.crud.opeartion.service;

import java.util.List;

import com.servlet.jsp.crud.opeartion.enity.Student;

public interface StudentService {
	public void createStudent(Student student);
	public List<Student> getStudents();
	public boolean deleteStudent(String id);
	public Student getStudent(String id);
	public void updateStudent(String id, Student reqStudent);
}
