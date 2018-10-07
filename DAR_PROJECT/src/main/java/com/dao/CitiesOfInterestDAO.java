package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.beans.CitiesOfInterest;
import com.utils.HibernateUtility;

public abstract class CitiesOfInterestDAO {
	public CitiesOfInterestDAO() {
	}

	public static void addCityOfInterest(CitiesOfInterest city) {
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = session.beginTransaction();
		try {
			session.save(city);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		tx.commit();
		System.out.println("n city added \n");

	}
	
	public static void userOfCity(int id_abonne, int id_city) {
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		    session  = sessionFactory.getCurrentSession();
			session.beginTransaction();
			@SuppressWarnings("deprecation")
			Query query = session
					.createSQLQuery("Insert INTO Abonne_Cities(id_abo, id_city) VALUES (:value1, :value2)");
			query.setParameter("value1", id_abonne);
			query.setParameter("value2", id_city);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}
}
