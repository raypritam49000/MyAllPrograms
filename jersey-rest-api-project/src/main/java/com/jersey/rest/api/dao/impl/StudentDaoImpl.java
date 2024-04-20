package com.jersey.rest.api.dao.impl;

import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jersey.rest.api.dao.StudentDao;
import com.jersey.rest.api.dbConfig.HibernateUtils;
import com.jersey.rest.api.dto.StudentDto;
import com.jersey.rest.api.entity.Student;
import com.jersey.rest.api.mappers.StudentMapper;

public class StudentDaoImpl implements StudentDao {

	@Override
	public void createStudent(StudentDto studentDto) {
		Session session = HibernateUtils.getsessionFactory().openSession();
		Transaction trx = session.beginTransaction();
		session.save(StudentMapper.toEntity(studentDto));
		trx.commit();
	}

	@Override
	public List<StudentDto> getStudents() {
		Session session = HibernateUtils.getsessionFactory().openSession();
		return session.createQuery("From Student").getResultList().stream().map(student-> StudentMapper.toDto((Student) student)).toList();
	}

	@Override
	public boolean deleteStudent(String id) {
		boolean isDeleted = false;
		Session session = HibernateUtils.getsessionFactory().openSession();
		Transaction trx = session.beginTransaction();
		Student student = session.get(Student.class, id);
		if (Objects.nonNull(student)) {
			session.delete(student);
		}
		trx.commit();
		isDeleted = true;
		return isDeleted;
	}

	@Override
	public StudentDto getStudent(String id) {
		Session session = HibernateUtils.getsessionFactory().openSession();
		Student student = session.get(Student.class, id);
		return StudentMapper.toDto(student);
	}

	@Override
	public void updateStudent(String id, StudentDto reqStudent) {
		Session session = HibernateUtils.getsessionFactory().openSession();
		Student student = session.get(Student.class, id);
		Transaction trx = session.beginTransaction();
		if (Objects.nonNull(student)) {
			student.setName(reqStudent.getName());
			student.setEmail(reqStudent.getEmail());
			student.setCity(reqStudent.getCity());
			student.setContactNo(reqStudent.getContactNo());
			session.update(student);
		}
		trx.commit();
	}

}
