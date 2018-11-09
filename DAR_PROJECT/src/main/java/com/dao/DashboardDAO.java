package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.JSONObject;

import com.beans.Abonne;
import com.beans.Publication;
import com.google.gson.JsonObject;
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
		//user id nest pas utilisé 
		//werili la bd stp
		//had le fichier est changé tu vx le sauvegarder
		//c'est juste les commentaires oui okk
		//oui juste pour le test je selectionne toutes les publications okk
		Transaction tx = null;
		List result = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			/**
			 * SELECT * FROM Publication p WHERE p.pub_id in (SELECT pub_id FROM Abonne_cities WHERE id_user=User_id
			 */
			Query query = session.createQuery("from Publication p");
			result = query.list();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
		}

		return result;
	}

	public static String getOwnerUserName(String owner) {
		JsonObject ownerJO = new JsonObject();
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		//parce qu'il me faisait une exception donc j'ai changé (Transaction already activeat org.hibernate.engine.
		//transaction.internal.TransactionImpl.begin(TransactionImpl.java:52)
		Session session  = sessionFactory.getSessionFactory().openSession();//sessionFactory.getCurrentSession();
//		if (!session.isOpen()) {
//			session = sessionFactory.openSession();
//		}
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			String userNameOwner;
			Query query = session.createQuery("SELECT a.username FROM Abonne a WHERE a.ABONNE_id='" + owner + "'");
			userNameOwner = query.uniqueResult().toString();
			tx.commit();

			ownerJO.addProperty("userNameOwner", userNameOwner);

			//System.out.println("userNameOwner : "+ ownerJO);
			session.close();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
		}
		
		return ownerJO.get("userNameOwner").getAsString();
	}
}
