package com.servlet.jsp.crud.opeartion.dao.impl;

import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.servlet.jsp.crud.opeartion.dao.StudentDao;
import com.servlet.jsp.crud.opeartion.enity.Student;
import com.servlet.jsp.crud.opeartion.utils.HibernateUtils;

public class StudentDaoImpl implements StudentDao {

	@Override
	public void createStudent(Student student) {
		Session session = HibernateUtils.getsessionFactory().openSession();
		Transaction trx = session.beginTransaction();
		session.save(student);
		trx.commit();
	}

	@Override
	public List<Student> getStudents() {
		Session session = HibernateUtils.getsessionFactory().openSession();
		return session.createQuery("From Student").getResultList();
	}

	@Override
	public boolean deleteStudent(String id) {
		boolean isDeleted = false;
		Session session = HibernateUtils.getsessionFactory().openSession();
		Transaction trx = session.beginTransaction();
		Student student = session.get(Student.class, id);
		if(Objects.nonNull(student)) {
			session.delete(student);
		}
		trx.commit();
		isDeleted = true;
		return isDeleted;
	}

	@Override
	public Student getStudent(String id) {
		Session session = HibernateUtils.getsessionFactory().openSession();
		Student student = session.get(Student.class, id);
		return student;
	}

	@Override
	public void updateStudent(String id, Student reqStudent) {
		Session session = HibernateUtils.getsessionFactory().openSession();
		Student student = session.get(Student.class, id);
		Transaction trx = session.beginTransaction();
		if(Objects.nonNull(student)) {
			student.setName(reqStudent.getName());
			student.setEmail(reqStudent.getEmail());
			student.setCity(reqStudent.getCity());
			student.setContactNo(reqStudent.getContactNo());
			session.update(student);
		}
		trx.commit();
	}

}
