
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.beans.Abonne;

public class ExecuteDB {
	public static void main(String[] args) {

		StandardServiceRegistry registry;
		SessionFactory sessionFactory;
		registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

		MetadataSources sources = new MetadataSources(registry);

		Metadata metadata = sources.getMetadataBuilder().build();

		sessionFactory = metadata.getSessionFactoryBuilder().build();
		// Create typesafe ServiceRegistry object

		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();

		Abonne e1 = new Abonne();
		e1.setFirstname("Gaurav");
		e1.setLastname("Chawla");
		e1.setEmail("lac.charaf@gmail.com");
		e1.setUsername("charafus");

		session.save(e1);
		t.commit();
		System.out.println("successfully saved");
		sessionFactory.close();
		session.close();

	}
}