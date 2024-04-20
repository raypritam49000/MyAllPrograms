package com.servlet.jsp.crud.opeartion.dao;

import java.util.List;

import com.servlet.jsp.crud.opeartion.enity.Student;

public interface StudentDao {
	public void createStudent(Student student);

	public List<Student> getStudents();

	public boolean deleteStudent(String id);

	public Student getStudent(String id);

	public void updateStudent(String id, Student student);
}
