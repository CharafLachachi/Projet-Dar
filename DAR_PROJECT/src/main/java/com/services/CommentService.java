package com.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.beans.Abonne;
import com.beans.Commentaire;
import com.beans.Publication;
import com.dao.AbonneDAO;
import com.dao.CommentDAO;
import com.dao.PublicationDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public abstract class CommentService {
	public static JsonObject addComment(String commentJson) {
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(commentJson,JsonObject.class );
		
		Commentaire c = new Commentaire();
		c.setComment_text(json.get("comment_text").getAsString());
		
		Abonne abonne = AbonneDAO.getAbonneById(Integer.parseInt(json.get("comment_user_id").getAsString()));
		Publication publication = PublicationDAO.getPublicationById(Integer.parseInt(json.get("comment_id_pub").getAsString()));
		
		c.setPublication(publication);
		c.setAbonne(abonne);
		
		CommentDAO.addComment(c);
		
		JsonObject resp = new JsonObject();
		resp.addProperty("message", "sucess");
		
		return resp ;
		
	}
	
	public static List<JsonObject> getComments(String id_pub) {
		Publication publication = PublicationDAO.getPublicationById(Integer.parseInt(id_pub));
		 Hibernate.initialize(publication.getComments());
		 List<Commentaire> comments = publication.getComments();
		List<JsonObject> list = new ArrayList<>();
		for (Commentaire commentaire : comments) {
			JsonObject comment = new JsonObject();
			comment.addProperty("comment_user_id", commentaire.getComment_id());
			comment.addProperty("comment_user_name", commentaire.getAbonne().getFirstname() + " " + commentaire.getAbonne().getLastname());
			comment.addProperty("comment_text", commentaire.getComment_text());
			comment.addProperty("comment_id_pub", id_pub);
			comment.addProperty("comment_created_date", commentaire.getCreated().toString());
			list.add(comment);			
		}
		
		return list;
		
	}
}
