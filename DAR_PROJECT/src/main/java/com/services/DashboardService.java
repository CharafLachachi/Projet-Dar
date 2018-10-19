package com.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jboss.jandex.TypeTarget.Usage;

import com.beans.Abonne;
import com.beans.Publication;
import com.utils.HibernateUtility;

public class DashboardService {

	public String getPublications() {
		return null;
	}
	
	public static List<Publication> getPublications(String userID) {
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = null;
		Publication publication = null;
		List result = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			/**
			 * SELECT * FROM Publication p WHERE p.pub_id in (SELECT pub_id FROM Abonne_cities WHERE id_user=User_id
			 */
			Query query = session.createQuery("from Publication p");
			//query.setParameter("id", userID);
			result = query.list();
			//tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
		}
		return result;
	}
}
