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
import com.utils.HibernateUtility;

public abstract class DashboardService {
	public static DashboardDAO dao = new DashboardDAO();

	public String getPublications() {
		return null;
	}

	public static String getDashBoardPublicationsByUserId(String userID) {
		List list = dao.getPublications(userID);

		ObjectMapper mapper = new ObjectMapper();
		System.err.println(list.size());
		String jsonInString = null ;
		try {
			jsonInString = mapper.writeValueAsString(list);
			System.out.println(jsonInString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("----"+jsonInString);
		return jsonInString;

	}
}
