package com.services;

import com.beans.Abonne;
import com.dao.AbonneDAO;
import com.google.gson.JsonObject;

public class SignUpService {
	public JsonObject createAbonne(String username, String firstname, String lastname, String email,String password ) {
		JsonObject abonne_json = null;
		//Abonne existedUsername_Abonne = AbonneDAO.getAbonneByUserName(username);
		//if (existedUsername_Abonne != null) {
			// TODO change with constructor later
			Abonne abonne = new Abonne();
			abonne.setUsername(username);
			abonne.setFirstname(firstname);
			abonne.setLastname(lastname);
			abonne.setEmail(email);
			abonne.setPassword(password);
			AbonneDAO.addAbonne(abonne);
			
			abonne_json = new JsonObject();
			abonne_json.addProperty("id_abonne", abonne.getABONNE_id());
			abonne_json.addProperty("message", 200);
			return abonne_json;
//		}
//		else {
//			abonne_json = new JsonObject();
//			abonne_json.addProperty("message", "Username : "+username+" exist");
//		}
		//return abonne_json;
	}
}
