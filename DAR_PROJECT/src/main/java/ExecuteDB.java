
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.beans.Abonne;
import com.beans.Publication;
import com.dao.DashboardDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.services.DashboardService;
import com.utils.HibernateUtility;
import com.utils.PasrseJsonUtility;

public class ExecuteDB {
	public static void main(String[] args) {
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
			String owner = "1";
			String userNameOwner;
			Query query = session.createQuery("SELECT a.username FROM Abonne a WHERE a.ABONNE_id='" + owner + "'");
			userNameOwner = query.uniqueResult().toString();
			tx.commit();
			
			JsonObject ownerJO = new JsonObject();
			ownerJO.addProperty("ownerusername", userNameOwner);
			
			System.out.println("userNameOwner : "+ ownerJO.get("ownerusername").getAsString());

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
		}


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