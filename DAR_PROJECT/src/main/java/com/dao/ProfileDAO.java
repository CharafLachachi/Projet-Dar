package com.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.beans.Abonne;
import com.beans.CitiesOfInterest;
import com.beans.Publication;
import com.google.gson.JsonObject;
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
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive())
			tx.begin();
		
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
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive())
			tx.begin();
		
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
	
	
public List GetPersonnalPosts(String UserID) {
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = null;
		List OwnPublications = null;
		
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
		if(OwnPublications == null) OwnPublications = new ArrayList<>(0);
		return OwnPublications; 
	}

	public List GetPostsOfInterst(String UserID) {
	
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = null;
		List PublicationsOfInterest = null;
		
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

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} 

		if(PublicationsOfInterest == null) PublicationsOfInterest = new ArrayList<>(0);
		return PublicationsOfInterest ;
	}
	
	
public String updateProfileInfos(JsonObject json) {
		
		String res = ""; 
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive())
			tx.begin();
		
		
		try {
			  
			  Abonne a = (Abonne) session.get(Abonne.class,json.get("user_id").getAsInt());
			  a.setFirstname(json.get("firstname").getAsString());
			  a.setLastname(json.get("lastname").getAsString());
			  a.setUsername(json.get("username").getAsString());
			  String pass = null ; 
			  if( ( pass = json.get("password").getAsString()).length()  >= 5)
			  a.setPassword(pass);
			 
			  Query query = session.createQuery("FROM Abonne a WHERE a.email='" + json.get("email").getAsString() + "'");
			  List l = query.list();
			  if(l.size() == 0)
				  a.setEmail(json.get("email").getAsString());
			
			  Set<CitiesOfInterest> cities_set = new HashSet<>();
			  if( ! json.get("cities").getAsString().equals("empty") ) {
				  
				  String[] cities = json.get("cities").getAsString().split("/"); 
				
				  CitiesOfInterest city;
				  for(String s : cities) {
					  city = new CitiesOfInterest();
					  city.setCity_name(s);
					  cities_set.add( city );
				  }
			  }
			  a.setCities( cities_set );
			  
			  session.save(a);
			  
			  if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
				    tx.commit();
			  }
			  
			    session  = sessionFactory.getCurrentSession();
				if (!session.isOpen()) {
					session = sessionFactory.openSession();
				}
			    tx = session.getTransaction();
				if(!tx.isActive())
					tx.begin();
				
					Algorithm algorithm = Algorithm.HMAC256("secret");
					Builder token = JWT.create();
					token.withClaim("id", a.getABONNE_id());
					token.withClaim("email", a.getEmail());
					token.withClaim("firstname", a.getFirstname());
					token.withClaim("username", a.getUsername());
					token.withClaim("lastname", a.getLastname());
							
					// i used eager fetch 
					 cities_set = a.getCities();
					 String cities_of_interest = ""; 
					
					 if(cities_set.size() == 0)
						cities_of_interest = "empty"; 
					 else {
						 
						 int i=0; 
						 
						 for(CitiesOfInterest c : cities_set) {
							cities_of_interest += c.getCity_name();
						 	i++;
						 	if(i < cities_set.size() ) cities_of_interest+= "/" ; 
						 }
					 }
					token.withClaim("cities", cities_of_interest);
					res = token.sign(algorithm);
			
			
					if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
					    tx.commit();
					}
				
				
				
			}catch (Throwable t) {
				tx.rollback();
				t.printStackTrace();
			
			}finally {
				session.close();
			}
			
			return res; 
		
	}
	
	
	
	
	
	
}
