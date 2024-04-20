package com.jersey.rest.api.mappers;

import com.jersey.rest.api.dto.StudentDto;
import com.jersey.rest.api.entity.Student;

public class StudentMapper {

	public static StudentDto toDto(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setCity(student.getCity());
		studentDto.setContactNo(student.getContactNo());
		studentDto.setEmail(student.getEmail());
		studentDto.setName(student.getName());
		studentDto.setId(student.getId());
		return studentDto;
	}

	public static Student toEntity(StudentDto studentDto) {
		Student student = new Student();
		student.setCity(studentDto.getCity());
		student.setContactNo(studentDto.getContactNo());
		student.setEmail(studentDto.getEmail());
		student.setName(studentDto.getName());
		student.setId(studentDto.getId());
		return student;
	}
}
