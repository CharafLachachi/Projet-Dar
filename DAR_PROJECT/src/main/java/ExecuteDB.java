
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.beans.Abonne;
import com.beans.Publication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.DashboardService;
import com.utils.HibernateUtility;

public class ExecuteDB {
	public static void main(String[] args) {

//		StandardServiceRegistry registry;
//		SessionFactory sessionFactory;
//		registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
//
//		MetadataSources sources = new MetadataSources(registry);
//
//		Metadata metadata = sources.getMetadataBuilder().build();
//
//		sessionFactory = metadata.getSessionFactoryBuilder().build();
//		// Create typesafe ServiceRegistry object
//
//		Session session = sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		if (getAbonneByUserName("charafus") == null) {
//			
//		
//		Abonne e1 = new Abonne();
//		e1.setFirstname("Gaurav");
//		e1.setLastname("Chawla");
//		e1.setEmail("lac.charaf@gmail.com");
//		e1.setUsername("charafus");
//
//		session.save(e1);
//		t.commit();
//		System.out.println("successfully saved");
//		sessionFactory.close();
//		session.close();
//		}else {
//			System.err.println("username exist");
//		}
//			List<Publication> list = DashboardService.getPublications("1");
//			ObjectMapper mapper = new ObjectMapper();
//			//	Staff obj = new Staff();
//
//
//			//Object to JSON in String
//			System.err.println(list.size());
//			try {
//				String jsonInString = mapper.writeValueAsString(list);
//				System.out.println(jsonInString);
//			} catch (JsonProcessingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

	}
	
	public static Abonne getAbonneByUserName(String username) {
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session  = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
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
		}
		return abonne;

	}
}