package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.beans.Publication;
import com.utils.HibernateUtility;

public class DashboardDAO {

	public DashboardDAO() {
	}

	public static List<Publication> getPublications(String userID) {
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = null;
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
