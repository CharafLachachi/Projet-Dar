package com.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.beans.Abonne;
import com.utils.HibernateUtility;

public abstract class AbonneDAO {

	public AbonneDAO() {
	}

	public static void addAbonne(Abonne abonne) {
		Session session = HibernateUtility.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.save(abonne);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();
		System.out.println("n Abonne added \n");

	}

	public static Abonne getAbonneByUserName(String username) {
		Session session = HibernateUtility.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		Abonne abonne = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from Abonne a where a.username='" + username + "'");
			abonne = (Abonne) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return abonne;

	}
}
