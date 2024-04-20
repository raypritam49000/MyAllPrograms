package com.jersey.rest.api.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jersey.rest.api.dao.UserDao;
import com.jersey.rest.api.dbConfig.HibernateUtils;
import com.jersey.rest.api.entity.User;

public class UserDaoImpl implements UserDao {

	@Override
	public User findUserByEmail(String email) {
		User user;
		try {
			Session session = HibernateUtils.getsessionFactory().openSession();
			Query query = session.createQuery("from User u where u.email = :email");
			query.setParameter("email", email);
			user = (User) query.getSingleResult();
		} catch (NoResultException e) {
			user = null;
		}

		return user;
	}

	@Override
	public void createUser(User user) {
		Session session = HibernateUtils.getsessionFactory().openSession();
		Transaction trx = session.beginTransaction();
		session.save(user);
		trx.commit();
	}

}
