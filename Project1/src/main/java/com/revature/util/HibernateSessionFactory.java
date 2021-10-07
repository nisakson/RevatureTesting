package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
 * We are no longer explicitly using JDBC, so we don't need our ConnectionFactory.
 * That said, we will need a way to pass Hibernate sessions around our
 * application. As such, we'll build our SessionFactory, which will return
 * sessions based on the configuration details specified in our
 * hibernate.cfg.xml file, here.
 */
public class HibernateSessionFactory {

	/*
	 * There is only ever going to be a single SessionFactory
	 * in this application. This follows the singleton design
	 * pattern.
	 */
	private static SessionFactory sessionFactory;
	
	public static Session getSession() {
		if(sessionFactory == null) {
			sessionFactory = new Configuration().configure()
					.setProperty("hibernate.connection.url", "jdbc:mariadb://database-1.ccrw0a9rrj8m.us-east-2.rds.amazonaws.com:3306/Expenses")
					.setProperty("hibernate.connection.username", "admin")
					.setProperty("hibernate.connection.password", "October20!")
					.buildSessionFactory();
		}
		
		return sessionFactory.getCurrentSession();
	}
}
