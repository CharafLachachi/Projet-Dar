
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
		String res = DashboardService.getDashBoardPublicationsByUserId("1");
		System.out.println(">>>>"+res);
		// hadi c'est juste our tester 
		// je t'explique okk
		/**
		 * 3endek dashboardservice dao .. c'est les classes qui s'occupe d'afficher les publication 
		 * je te montre sur le client 
		 * t'as vu ?
		 * yes
		 * donc pour ça on utilisais un mapper pour formatter les resultats de la bdd 
		 * mais problème c'est qu'il retourne pas toutes les publication et en plus il rajoute des json bizarre pour regler les relations 
		 * circulaires u'on f la bdd 
		 * donc derna hadik la boucle 
		 * en local ça prend 193 ms mais quand on a hebergé welat + de 30smoi la b
		 * tu veux voir la bd ?
		 * att werili 9bel la bcl f local
		 * et hada achou ?
		 * le main ta3o est vide
		 * on l'utilise pour le test*
		 * okeey
		 * faudra faire une requete pour appeler la servlet et tester
		 * vasyy
		 */ 
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