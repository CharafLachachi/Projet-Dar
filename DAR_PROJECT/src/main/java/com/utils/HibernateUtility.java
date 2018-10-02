package com.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {

	private static final SessionFactory sessionFactory;

//	static {
//		try {
////			// Créer une SessionFactory à partir de hibernate.cfg.xml
////			StandardServiceRegistry registry;
////			registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
////
////			MetadataSources sources = new MetadataSources(registry);
////
////			Metadata metadata = sources.getMetadataBuilder().build();
////
////			sessionFactory = metadata.getSessionFactoryBuilder().build();
////			// Create typesafe ServiceRegistry object
//
//		} catch (Throwable ex) {
//			// Gestion exception
//			System.err.println("Echec création SessionFactory" + ex);
//			throw new ExceptionInInitializerError(ex);
//		}
//	}
//
//	public static SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
	

	    static {
	        try {
	            // Créer une SessionFactory à partir de hibernate.cfg.xml
	            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	        } catch (Throwable ex) {
	            // Gestion exception
	            System.err.println("Echec création SessionFactory" + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

}