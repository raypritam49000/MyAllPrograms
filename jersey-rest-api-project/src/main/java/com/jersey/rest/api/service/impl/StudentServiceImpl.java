package com.jersey.rest.api.service.impl;

import java.util.List;

import com.jersey.rest.api.dao.StudentDao;
import com.jersey.rest.api.dao.impl.StudentDaoImpl;
import com.jersey.rest.api.dto.StudentDto;
import com.jersey.rest.api.service.StudentService;


public class StudentServiceImpl implements StudentService {

	@Override
	public void createStudent(StudentDto student) {
		StudentDao studentDao = new StudentDaoImpl();
		studentDao.createStudent(student);
	}

	@Override
	public List<StudentDto> getStudents() {
		StudentDao studentDao = new StudentDaoImpl();
		return studentDao.getStudents();
	}

	@Override
	public boolean deleteStudent(String id) {
		StudentDao studentDao = new StudentDaoImpl();
		return studentDao.deleteStudent(id);
	}

	@Override
	public StudentDto getStudent(String id) {
		StudentDao studentDao = new StudentDaoImpl();
		return studentDao.getStudent(id);
	}

	@Override
	public void updateStudent(String id, StudentDto reqStudent) {
		StudentDao studentDao = new StudentDaoImpl();
		studentDao.updateStudent(id,reqStudent);
	}
	
	

}
