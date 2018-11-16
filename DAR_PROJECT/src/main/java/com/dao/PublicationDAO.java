package com.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.beans.Abonne;
import com.beans.AddressModel;
import com.beans.CitiesOfInterest;
import com.beans.HotelContactModel;
import com.beans.Publication;
import com.beans.WeatherModel;
import com.utils.HibernateUtility;

public abstract class PublicationDAO {

	public static void addPublic(Publication pub, int ownerId) {
		// Step 1 Add address :
		// address is not persisted
		AddressModel address = pub.getAddress();

		// Step2 Add contact :
		HotelContactModel contact = pub.getHotelContacts();

		// Step 3 add Weather
		WeatherModel weather =  pub.getWeather();
		
		// Step 4 Add Publication
		
		Session session = null;
		
		// step 0 verify city exist
		
		CitiesOfInterest city = CitiesOfInterestDAO.getCityByName(pub.getCity());
		CitiesOfInterest c = null;
		if (city == null ) {
		 c = new CitiesOfInterest();
		c.setCity_name(pub.getCity());
		CitiesOfInterestDAO.addCityOfInterest(c);
		CitiesOfInterestDAO.userOfCity(AbonneDAO.getAbonneById(ownerId).getABONNE_id(), c.getCity_id());
		}
		if(c!=null)
		pub.setCity(String.valueOf(c.getCity_name()));
		

		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(address);
			session.getTransaction().commit();
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(contact);
			session.getTransaction().commit();
			
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(weather);
			session.getTransaction().commit();	

			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(pub);
			session.getTransaction().commit();
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			weather.setPub(pub);
			session.update(weather);
			session.getTransaction().commit();
			
			userOfPub(AbonneDAO.getAbonneById(ownerId).getABONNE_id(), pub.getPub_id());
		}

		catch (HibernateException e) {

			e.printStackTrace();

			session.getTransaction().rollback();

		}

	}
	
	public static void userOfPub(int id_abonne, int id_pub) {
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		    session  = sessionFactory.getCurrentSession();
			session.beginTransaction();
			@SuppressWarnings("deprecation")
			Query query = session
					.createSQLQuery("Insert INTO AbonnePub(abo_id, pub_id) VALUES (:value1, :value2)");
			query.setParameter("value1", id_abonne);
			query.setParameter("value2", id_pub);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}
	
	public static Publication getPublicationById(int id_pub) {
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		Transaction tx = null;
		Publication pub = null;
		try {
			tx = session.getTransaction();
			if(!tx.isActive()) 
			tx.begin();
			pub =( Publication) session.get(Publication.class, id_pub);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
		}
		return pub;
	}
	
}
