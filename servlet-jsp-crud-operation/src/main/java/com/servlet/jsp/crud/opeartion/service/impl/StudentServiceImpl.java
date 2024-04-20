package com.servlet.jsp.crud.opeartion.service.impl;

import java.util.List;

import com.servlet.jsp.crud.opeartion.dao.StudentDao;
import com.servlet.jsp.crud.opeartion.dao.impl.StudentDaoImpl;
import com.servlet.jsp.crud.opeartion.enity.Student;
import com.servlet.jsp.crud.opeartion.service.StudentService;

public class StudentServiceImpl implements StudentService {

	@Override
	public void createStudent(Student student) {
		StudentDao studentDao = new StudentDaoImpl();
		studentDao.createStudent(student);
	}

	@Override
	public List<Student> getStudents() {
		StudentDao studentDao = new StudentDaoImpl();
		return studentDao.getStudents();
	}

	@Override
	public boolean deleteStudent(String id) {
		StudentDao studentDao = new StudentDaoImpl();
		return studentDao.deleteStudent(id);
	}

	@Override
	public Student getStudent(String id) {
		StudentDao studentDao = new StudentDaoImpl();
		return studentDao.getStudent(id);
	}

	@Override
	public void updateStudent(String id, Student reqStudent) {
		StudentDao studentDao = new StudentDaoImpl();
		studentDao.updateStudent(id,reqStudent);
	}
	
	

}
