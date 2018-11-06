package com.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.beans.Abonne;
import com.beans.AddressModel;
import com.beans.CitiesOfInterest;
import com.beans.Commentaire;
import com.beans.HotelContactModel;
import com.beans.Message;
import com.beans.Publication;
import com.beans.WeatherModel;

public class HibernateUtility {
	  private static StandardServiceRegistry registry;
	private static  SessionFactory sessionFactory;

	
	public static SessionFactory getSessionFactory() {
//	    if (sessionFactory == null) {
//	      try {
//	        // Create registry
//	        registry = new StandardServiceRegistryBuilder()
//	            .configure()
//	            .build();
//
//	        // Create MetadataSources
//	        MetadataSources sources = new MetadataSources(registry);
//
//	        // Create Metadata
//	        Metadata metadata = sources.getMetadataBuilder().build();
//
//	        // Create SessionFactory
//	        sessionFactory = metadata.getSessionFactoryBuilder().build();
//
//	      } catch (Exception e) {
//	        e.printStackTrace();
//	        if (registry != null) {
//	          StandardServiceRegistryBuilder.destroy(registry);
//	        }
//	      }
//	    }
//	    return sessionFactory;
		
		try {
	
		     Configuration cfg = new Configuration()
			
			
			.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
			.setProperty("hibernate.connection.url", "jdbc:mysql://dar-mysql-server.mysql.database.azure.com/dar_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
			.setProperty("hibernate.connection.username", "charaf@dar-mysql-server")
			.setProperty("hibernate.connection.password", "Dar+2018")
			
			.setProperty("hibernate.show_sql","true")
			.setProperty("hibernate.format_sql", "true")
			.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect")
			.setProperty("hibernate.hbm2ddl.auto","update")
			.setProperty("hibernate.current_session_context_class", "thread")
			.setProperty("hibernate.id.new_generator_mappings", "false")
			
			.addPackage("com.beans")
			.addAnnotatedClass(Abonne.class)
			.addAnnotatedClass(AddressModel.class)
			.addAnnotatedClass(CitiesOfInterest.class)
			.addAnnotatedClass(Commentaire.class)
			.addAnnotatedClass(HotelContactModel.class)
			.addAnnotatedClass(Message.class)
			.addAnnotatedClass(Publication.class)
			.addAnnotatedClass(WeatherModel.class);
		
		     
		     StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		    	      .applySettings(cfg.getProperties());
		    	  return cfg.buildSessionFactory(builder.build());
			
			//return cfg;

		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("ERROR >>>>>> "+ e.getMessage());
		}

		return null;
	  }

	  public static void shutdown() {
	    if (registry != null) {
	      StandardServiceRegistryBuilder.destroy(registry);
	    }
	  }
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
	
//
//	    static {
//	        try {
//	            // Créer une SessionFactory à partir de hibernate.cfg.xml
//	            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
//	            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
//	            		applySettings(configuration.getProperties());
//	            sessionFactory = configuration.buildSessionFactory(builder.build());
//
//	        } catch (Throwable ex) {
//	            // Gestion exception
//	            System.err.println("Echec création SessionFactory" + ex);
//	            throw new ExceptionInInitializerError(ex);
//	        }
//	    }
//
//	    public static SessionFactory getSessionFactory() {
//	        return sessionFactory;
//	    }

}