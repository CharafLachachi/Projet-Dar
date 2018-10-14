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

	public static List<Publication> getAbonneByUserName(String userID) {
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
			Query query = session.createQuery("from Publication a where a.pub_id in (from =:id + ");
			query.setParameter("id", userID);
			result = query.list();
			tx.commit();
			List<Publication> pubs = new ArrayList<Publication>();
			for( int i=0 ; i < result.size() ; i++ ) {
				pubs.set(i, (Publication) result.get(i));
			}

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
