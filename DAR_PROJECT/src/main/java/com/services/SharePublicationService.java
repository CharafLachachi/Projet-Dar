package com.services;

import com.beans.Publication;
import com.dao.PublicationDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public abstract class SharePublicationService {
	public static boolean addPublictaion(StringBuffer pubJson) {
		boolean res = false;
		int ownerId = new Gson().fromJson(pubJson.toString(),JsonObject.class).getAsJsonObject().get("idUser").getAsInt();
		
		Publication pub = new Gson().fromJson(pubJson.toString(), Publication.class);
		System.out.println(pub.toString());
		pub.setOwner(ownerId);
		PublicationDAO.addPublic(pub,ownerId);
		return res;
	}
}
