package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.beans.Abonne;
import com.utils.HibernateUtility;

public abstract class AbonneDAO {

	public AbonneDAO() {
	}

	public static void addAbonne(Abonne abonne) {
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = session.beginTransaction();
	
		try {
			session.save(abonne);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
		    tx.commit();
		}
		System.out.println("n Abonne added \n");

	}
	
	public static Abonne getAbonneById(int id) {
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = null;
		Abonne abonne = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("FROM Abonne a WHERE a.ABONNE_id='" + id + "'");
			abonne = (Abonne) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
		}
		return abonne;

	}
	public static Abonne getAbonneByUserName(String email) {
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = null;
		Abonne abonne = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("FROM Abonne a WHERE a.email='" + email + "'");
			abonne = (Abonne) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
		}
		return abonne;

	}
	
	public static Abonne getAbonneByUserNameAndPassword(String username,String password) {
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = null;
		Abonne abonne = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from Abonne a where a.username='" + username + "' and  a.password ='"+ password+ "'");
			abonne = (Abonne) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
		}
		return abonne;

	}
	
	
}
