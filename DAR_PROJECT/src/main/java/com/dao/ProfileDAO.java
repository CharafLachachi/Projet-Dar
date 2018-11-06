package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.beans.Abonne;
import com.beans.Publication;
import com.utils.HibernateUtility;

public class ProfileDAO {

	private static ProfileDAO p_dao = null ;

	public static ProfileDAO get_instance() {
		
		if(p_dao == null) {	
			p_dao = new ProfileDAO();
		}
		
		return p_dao ;
	}

	private ProfileDAO() {
		
	}
	
	// sebhan lah 
			//-_-_
			//au fait quand tu fais query.list tu commit et toi t'es en train de commit une deuxième fois 
			// mais j'ai testé sans commit ça n'avait pas marché et toi t'as pas lancé le serveur pk ? 
			//faut pas lancer le serveur je t'ai dis ça c'est une classe de test tu lance que ça
			//du coup c'est pour ça ça archait pas
			//normalement ça n'a rien a voir atend je ressaye -_-
			// sebhan lah 
			//mdrrr mytho -_- wllh j'ai testé sans commit hier xD
			//xD ihechkoulen merci jesus -_-
			//aller salut :D sahit beslama saha 
	
	public ArrayList<List> getAllPublications(String UserID) {
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = null;
		List OwnPublications = null;
		List PublicationsOfInterest = null;
		
		try {
				tx = session.getTransaction();
				if(!tx.isActive())
					tx.begin();
		     	Query query = session.createQuery("FROM Publication p WHERE p.owner='" + UserID + "'");
				OwnPublications = query.list();
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 

		try {
			tx = session.getTransaction();
			if(!tx.isActive())
				tx.begin();
			
			
			String q = "SELECT pub_id FROM AbonnePub WHERE abo_id='"+UserID+"'";
			String jpql = "FROM Publication p WHERE p.Pub_id IN :ids";
			Query query = session.createSQLQuery(q);
			List l = query.list();
			if(l.size() > 0) {
				query = session.createQuery(jpql);
				query.setParameter("ids", l);
				PublicationsOfInterest = query.list();
			}
	//		String q = "SELECT * FROM PUBLICATION WHERE PUB_ID IN ( SELECT pub_id FROM AbonnePub WHERE abo_id='"+UserID+"')";
	//		String q = "FROM Publication p WHERE p.Pub_id IN ( SELECT pub_id FROM p.abonnes WHERE ABONNE_id='"+UserID+"')";		
	//		Query query = session.createSQLQuery(q);
	//		 PublicationsOfInterest = query.list();		
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 
		ArrayList<List> res  = new ArrayList<>();
		if(OwnPublications == null) OwnPublications = new ArrayList<>(0);
		if(PublicationsOfInterest == null) PublicationsOfInterest = new ArrayList<>(0);
		res.add(OwnPublications);
		res.add(PublicationsOfInterest);
		return res;
		
	}
	
	
	public int DeleteOwnPublication(String PublicationID) {
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = null;
		int res ;
		try {
		  
		  tx = session.getTransaction();
		  if (!tx.isActive())
			  tx.begin();
		  String hql = "delete from Publication where Pub_id='"+PublicationID+"'";
		  Query query = session.createQuery(hql);
		  res = query.executeUpdate();
		  tx.commit();

		} catch (Throwable t) {
		  tx.rollback();
		  t.printStackTrace();
		  return -1;
		}
		
		return res;
	}
	
	public int	DeletePublicationOfInterest(String UserID,String PublicationID) {
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = null;
		int res ;
		try {
		  
		  tx = session.getTransaction();
		  if(!tx.isActive())
		  tx.begin();
		  String sql = "delete from AbonnePub where abo_id='"+UserID+"' and pub_id='"+PublicationID+"'";
		  Query query = session.createSQLQuery(sql);
		  res = query.executeUpdate();
		  
		  int pub_id = Integer.parseInt(PublicationID);
		  Publication p = (Publication) session.get(Publication.class,pub_id);
		  p.setNbPers(p.getNbPers() > 0 ? p.getNbPers()-1 : 0);
		  
		  tx.commit();

		} catch (Throwable t) {
		  tx.rollback();
		  return -1;
		}
		
		return res;
		
	}
	
	public byte[] getProfilePicture(int user_id) {
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		
		byte[] image = null;
		
		 Transaction  tx = session.beginTransaction();
		try {
		 
		  Abonne a = (Abonne) session.get(Abonne.class,user_id);
		  image = a.getImage();
		  if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
			    tx.commit();
			}
		} catch (Throwable t) {
			tx.rollback();
			t.printStackTrace();
		
		}finally {
			session.close();
		}
	
		return image;
		
	}
	
	public byte[] uploadProfilePicture(int user_id, byte[] image ) {
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		
		Transaction tx = session.beginTransaction();
		try {
		  
		  Abonne a = (Abonne) session.get(Abonne.class,user_id);
		  a.setImage(image);
		  if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
			    tx.commit();
		  }
		} catch (Throwable t) {
			tx.rollback();
			t.printStackTrace();
		
		}finally {
			session.close();
		}
	
		return image;
		
	}
	
}
