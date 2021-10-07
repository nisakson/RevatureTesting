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
public class ReimbursementRepository {

	public List<Reimbursement> findAll(){
		Session session = null;
		Transaction tx = null;
		List<Reimbursement> lists = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> rootEntry = cq.from(Reimbursement.class);
			CriteriaQuery<Reimbursement> all = cq.select(rootEntry);
		
			TypedQuery<Reimbursement> allQuery = session.createQuery(all);
			lists = allQuery.getResultList();
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return lists;
	}
	
	public List<Reimbursement> findName(String name){
		Session session = null;
		Transaction tx = null;
		List<Reimbursement> lists = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> rootEntry = cq.from(Reimbursement.class);
			CriteriaQuery<Reimbursement> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("employee_name"), name));
		
			TypedQuery<Reimbursement> allQuery = session.createQuery(all);
			lists = allQuery.getResultList();
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return lists;
	}
	
	public void save(Reimbursement reimbursement) {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.save(reimbursement);
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public void approve(int id) {
		Session session = null;
		Transaction tx = null;
		Reimbursement news = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> rootEntry = cq.from(Reimbursement.class);
			CriteriaQuery<Reimbursement> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("reimbursement_id"), id));
		
			TypedQuery<Reimbursement> allQuery = session.createQuery(all);
			news = allQuery.getSingleResult();
			
			news.setReimbursement_status("Approved");
			
			session.saveOrUpdate(news);
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public void deny(int id) {
		Session session = null;
		Transaction tx = null;
		Reimbursement news = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> rootEntry = cq.from(Reimbursement.class);
			CriteriaQuery<Reimbursement> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("reimbursement_id"), id));
		
			TypedQuery<Reimbursement> allQuery = session.createQuery(all);
			news = allQuery.getSingleResult();
			
			news.setReimbursement_status("Denied");
			
			session.saveOrUpdate(news);
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	// Additional methods only used during testing to "reset" the database
	public void delete(Reimbursement reimbursement) {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.delete(reimbursement);;
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public void pending(int id) {
		Session session = null;
		Transaction tx = null;
		Reimbursement news = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> rootEntry = cq.from(Reimbursement.class);
			CriteriaQuery<Reimbursement> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("reimbursement_id"), id));
		
			TypedQuery<Reimbursement> allQuery = session.createQuery(all);
			news = allQuery.getSingleResult();
			
			news.setReimbursement_status("Pending");
			
			session.saveOrUpdate(news);
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
