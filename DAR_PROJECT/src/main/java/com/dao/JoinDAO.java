package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.beans.Publication;
import com.utils.HibernateUtility;

public class JoinDAO {

	public JoinDAO() {}

	public static void JoinPublication(int PublicationId, int userId ) {
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			
//			String sql1 = "UPDATE PUBLICATION SET nbPers=nbPers+1 WHERE pub_id="+PublicationId;
//			Query query1 = session.createSQLQuery(sql1);
//			int res1 = query1.executeUpdate();
			
			String sql = "INSERT INTO AbonnePub VALUES("+userId+","+PublicationId+")";
			Query query = session.createSQLQuery(sql);
			int res = query.executeUpdate();

			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
		}
	}
	
}
