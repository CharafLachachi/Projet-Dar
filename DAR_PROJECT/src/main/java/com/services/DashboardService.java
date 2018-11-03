package com.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jboss.jandex.TypeTarget.Usage;

import com.beans.Abonne;
import com.beans.Publication;
import com.dao.DashboardDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.utils.HibernateUtility;

public abstract class DashboardService {
	public static DashboardDAO dao = new DashboardDAO();

	public static String[] getDashBoardPublicationsByUserId(String userID) {
		List res = dao.getPublications(userID);

		ObjectMapper mapper = new ObjectMapper();
		System.err.println(res.size());


		String jsonInString = null ;
		String[] t = null;

		try {
			t = new String[res.size()];
			for(int i=0;i<res.size();i++) {
				t[i] = mapper.writeValueAsString(res.get(i));
				System.out.println(t[i]);
			}
			jsonInString = mapper.writeValueAsString(res.toArray()); 
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return t;
	}

	public static void addOwnerName(JsonObject publication) {

		String owner = publication.get("owner").getAsString();
		
		System.out.println("publication "+publication);
		
		String userNameOwner = DashboardDAO.getOwnerUserName(owner);
		
		//Add username owner to publication
		
		publication.addProperty("userNameOwner", userNameOwner);
		
		System.out.println("apres : "+publication);
	}
}
