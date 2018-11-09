package com.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.beans.Abonne;
import com.beans.Publication;
import com.dao.ProfileDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.HibernateUtility;

public class ShowProfileService {

	private static ProfileDAO profile_dao = ProfileDAO.get_instance();
	
	
	public static String DeleteOwnPublication(String PublicationID) {
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInString = null ;
		
		int res = profile_dao.DeleteOwnPublication(PublicationID);
		
		try {
			
			jsonInString = mapper.writeValueAsString(res > 0 ? 1 : 0);
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonInString;
		

	}
	
	public static String 	DeletePublicationOfInterest(String UserID,String PublicationID) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInString = null ;
		
		int res = profile_dao.DeletePublicationOfInterest(UserID,PublicationID);
		
		try {
			
			jsonInString = mapper.writeValueAsString(res > 0 ? 1 : 0);
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonInString;
		
	}

	/** returns all personnal publications of this user 
	 *  and all publications that he is interested in.
	 */
	public static String GetAllPublications(String userID) {
		
		ArrayList<List> res = profile_dao.getAllPublications(userID);

		ObjectMapper mapper = new ObjectMapper();
		String[] posts = null;
		try {
			  posts = new String[res.get(0).size()];
			 for(int i=0;i<res.get(0).size();i++) {
				 posts[i] = mapper.writeValueAsString(res.get(0).get(i));
				 System.out.println(posts[i]);
			 }
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		String own_publications = "[";
		for(int i=0;i<posts.length;i++) {
			own_publications += posts[i];
			if(i<posts.length-1) own_publications  +=",";
		}
		own_publications  += "]";
	
		posts = null;
		try {
			  posts = new String[res.get(1).size()];
			 for(int i=0;i<res.get(1).size();i++) {
				 posts[i] = mapper.writeValueAsString(res.get(1).get(i));
				 //System.out.println(posts[i]);
			 }
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		

		String publications_of_interest = "[";
		for(int i=0;i<posts.length;i++) {
			publications_of_interest += posts[i];
			if(i<posts.length-1) publications_of_interest +=",";
		}
		publications_of_interest += "]";
		
		return "["+own_publications+","+publications_of_interest+"]";
	}
	
	
	public static byte[] getProfilePicture(String user_id) {
		
		int User_id = Integer.parseInt(user_id);
		return profile_dao.getProfilePicture(User_id);
		
	}
	
	public static byte[] uploadProfilePicture(String user_id, byte[] image ) {
		
		int User_id = Integer.parseInt(user_id);
		return profile_dao.uploadProfilePicture(User_id, image);
		
	}
}
