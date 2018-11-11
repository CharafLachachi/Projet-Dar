
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.api.UnsplashApiAccess;
import com.beans.Abonne;
import com.beans.Publication;
import com.dao.DashboardDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.services.DashboardService;
import com.services.SearchHotelsService;
import com.utils.HibernateUtility;
import com.utils.PasrseJsonUtility;

import helpers.models.SearchRequestModel;

public class ExecuteDB {
	public static void main(String[] args) {
//		String res = DashboardService.getDashBoardPublicationsByUserId("1");
//		System.out.println(">>>>"+res);
//		
//		SearchHotelsService searchHotelService = new SearchHotelsService();
//		String req  = "{\"price\":500,\"nbPers\":2,\"radius\":20,\"cities\":[\"Lyon, France\"],\"chekInDate\":\"2018-11-10T23:00:00.000Z\",\"checkOutDate\":\"2018-11-16T23:00:00.000Z\"}";
//		SearchRequestModel searchObject = (new Gson().fromJson(req, SearchRequestModel.class));
//
//		System.out.println(searchObject.getCities().toString());
//		String hoteslOffersResponse = searchHotelService.getHotels(searchObject);
//		UnsplashApiAccess api = new UnsplashApiAccess();
//		System.out.println(		api.getImage());
		
		try {
			String res = DashboardService.getDashBoardPublicationsByUserId("1"/*userId*/);
			System.out.println(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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