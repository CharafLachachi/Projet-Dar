package com.services;

import java.util.List;

import com.beans.Publication;
import com.dao.DashboardDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

public abstract class DashboardService {

	public static String getDashBoardPublicationsByUserId(String userID) {
		List<Publication> list = DashboardDAO.getPublications(userID);

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null ;
		try {
			jsonInString = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(jsonInString);
		return jsonInString;
	}

	public static void addOwnerName(JsonObject publication) {

		String owner = publication.get("owner").getAsString();

		//System.out.println("publication "+publication);

		String userNameOwner = DashboardDAO.getOwnerUserName(owner);

		//Add username owner to publication

		publication.addProperty("userNameOwner", userNameOwner);

		//System.out.println("apres : "+publication);
	}
}
