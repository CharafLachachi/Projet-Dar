package com.services;

import com.beans.Publication;
import com.dao.PublicationDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public abstract class SharePublicationService {
	public static boolean addPublictaion(StringBuffer pubJson) {
		boolean res = false;
		Publication jsonObject = new Gson().fromJson(pubJson.toString(), Publication.class);
		System.out.println(jsonObject.toString());
		PublicationDAO.addPublic(jsonObject);
		return res;
	}
}
