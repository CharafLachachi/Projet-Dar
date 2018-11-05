package com.services;

import java.util.List;

import com.dao.DashboardDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

public abstract class DashboardService {
	public static DashboardDAO dao = new DashboardDAO();

	public static String getDashBoardPublicationsByUserId(String userID) {
//				List res = dao.getPublications(userID);
//		
//				ObjectMapper mapper = new ObjectMapper();
//				System.err.println(res.size());
//		
//				String jsonInString = null ;
//				String[] t = null;

		
//				try {
//					t = new String[res.size()];
//					for(int i=0;i<res.size();i++) {
//						t[i] = mapper.writeValueAsString(res.get(i));
//						System.out.println(t[i]);
//					}
//					jsonInString = mapper.writeValueAsString(res.toArray()); 
//				} catch (JsonProcessingException e) {
//					e.printStackTrace();
//				}
		
				//return t;
		List list = dao.getPublications(userID);

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null ;
		try {
			jsonInString = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
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
