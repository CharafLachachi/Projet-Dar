package com.dao;

import java.util.List;

import javax.xml.stream.events.Comment;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.beans.Commentaire;
import com.beans.Publication;
import com.utils.HibernateUtility;

public abstract class CommentDAO {
	public static void addComment(Commentaire c) {
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = session.beginTransaction();
	
		try {
			session.save(c);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
		    tx.commit();
		}
	}
	
	public List<Commentaire> getComments(String pub_id) {
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
				Query query = session.createQuery("FROM Commentaire C WHERE C.PUB_ID =: pub_id ");
				query.setParameter("pub_id", pub_id);
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
		
		
}
