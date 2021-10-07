package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.util.HibernateSessionFactory;

//This is my DAO layer. It has been rewritten with Hibernate.
public class EmployeeRepository {

	public Employee getByUsername(String username){
		Session session = null;
		Transaction tx = null;
		Employee pass = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
			Root<Employee> rootEntry = cq.from(Employee.class);
			CriteriaQuery<Employee> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("employee_username"), username));
		
			TypedQuery<Employee> allQuery = session.createQuery(all);
			pass = allQuery.getSingleResult();
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return pass;
	}
}
