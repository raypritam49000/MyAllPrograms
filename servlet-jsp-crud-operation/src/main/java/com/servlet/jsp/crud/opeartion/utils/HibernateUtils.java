package com.servlet.jsp.crud.opeartion.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.servlet.jsp.crud.opeartion.enity.Student;

public class HibernateUtils {
	private static SessionFactory sessionFactory;

	public static SessionFactory getsessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				Properties settings = new Properties();
				settings.put(Environment.DRIVER,PropertyFileReader.getPropertyValue("db.driver-class-name"));
				settings.put(Environment.URL,PropertyFileReader.getPropertyValue("db.url"));
				settings.put(Environment.USER, PropertyFileReader.getPropertyValue("db.username"));
				settings.put(Environment.PASS, PropertyFileReader.getPropertyValue("db.password"));
				settings.put(Environment.DIALECT, PropertyFileReader.getPropertyValue("hibernate.dialect"));
				settings.put(Environment.SHOW_SQL,PropertyFileReader.getPropertyValue("hibernate.show_sql"));
				settings.put(Environment.FORMAT_SQL, PropertyFileReader.getPropertyValue("hibernate.format_sql"));
				settings.put(Environment.HBM2DDL_AUTO, PropertyFileReader.getPropertyValue("hibernate.hbm2ddl.auto"));

				configuration.setProperties(settings);
				 configuration.addAnnotatedClass(Student.class);
				sessionFactory = configuration.buildSessionFactory();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

}