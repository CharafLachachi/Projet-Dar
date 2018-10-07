package com.services;

import java.util.HashSet;
import java.util.Set;

import com.beans.Abonne;
import com.beans.CitiesOfInterest;
import com.dao.AbonneDAO;
import com.dao.CitiesOfInterestDAO;
import com.google.gson.JsonObject;

public class SignService {
	public JsonObject createAbonne(String username, String firstname, String lastname, String email,String password,String[] cities ) {
		JsonObject abonne_json = null;
		Abonne existedUsername_Abonne = AbonneDAO.getAbonneByUserName(email);
		if (existedUsername_Abonne == null) {
			 //TODO change with constructor later
			Abonne abonne = new Abonne();
			abonne.setUsername(username);
			abonne.setFirstname(firstname);
			abonne.setLastname(lastname);
			abonne.setEmail(email);
			abonne.setPassword(password);
			AbonneDAO.addAbonne(abonne);

			for (int i = 0; i < cities.length; i++) {
				// TODO verifier si la ville n'existe pas dans la bdd avant d'ajouter
				// TODO cas juste pour le test 
				// TODO cas reel si la ville exite on ajouter l'abonne dans la liste des abonnes de la ville 
				CitiesOfInterest city = new CitiesOfInterest();
				city.setCity_name(cities[i]);
				CitiesOfInterestDAO.addCityOfInterest(city);
				CitiesOfInterestDAO.userOfCity(abonne.getABONNE_id(), city.getCity_id());

				//abonne.getCities().add(city);
			}
			
			
			System.out.println("**************************************************"+abonne.toString());
			abonne_json = new JsonObject();
			abonne_json.addProperty("id_abonne", abonne.getABONNE_id());
			abonne_json.addProperty("message", 200);
			return abonne_json;
		}
		else {
			abonne_json = new JsonObject();
			abonne_json.addProperty("message", "Username : "+username+" exist");
		}
		return abonne_json;
	}
	
	public Abonne loginAbonne(String email, String password){
		Abonne abonne = AbonneDAO.getAbonneByUserName(email);
		return abonne;
	}
}
