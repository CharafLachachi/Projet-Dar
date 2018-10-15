package com.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.beans.AddressModel;
import com.beans.HotelContactModel;
import com.beans.Publication;
import com.beans.WeatherModel;
import com.utils.HibernateUtility;

public abstract class PublicationDAO {

	public static void addPublic(Publication pub) {
		// Step 1 Add address :
		// address is not persisted
		AddressModel address = pub.getAddress();

		// Step2 Add contact :
		HotelContactModel contact = pub.getContactHotel();

		// Step 3 add Weather
		WeatherModel weather =  pub.getWeather();
		
		// Step 4 Add Publication

		Session session = null;

		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(address);
			session.getTransaction().commit();
			session.beginTransaction();
			session.save(contact);
			session.getTransaction().commit();
			session.beginTransaction();
			session.save(weather);
			session.getTransaction().commit();
			
			session.beginTransaction();
			session.save(pub);
			session.getTransaction().commit();
		}

		catch (HibernateException e) {

			e.printStackTrace();

			session.getTransaction().rollback();

		}

	}
}
