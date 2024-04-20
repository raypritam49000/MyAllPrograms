package com.jersey.rest.api.dao;

import java.util.List;

import com.jersey.rest.api.dto.StudentDto;

public interface StudentDao {
	public void createStudent(StudentDto student);

	public List<StudentDto> getStudents();

	public boolean deleteStudent(String id);

	public StudentDto getStudent(String id);

	public void updateStudent(String id, StudentDto student);

}
