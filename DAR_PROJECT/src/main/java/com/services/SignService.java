package com.services;

import java.util.HashSet;
import java.util.Set;

import com.beans.Abonne;
import com.beans.CitiesOfInterest;
import com.dao.AbonneDAO;
import com.dao.CitiesOfInterestDAO;
import com.google.gson.JsonObject;

public class SignService {
	public JsonObject createAbonne(String username, String firstname, String lastname, String email,String password,String[] cities, String image ) {
		JsonObject abonne_json;
		try {
			abonne_json = null;
			Abonne existedUsername_Abonne = AbonneDAO.getAbonneByUserName(email);
			
			
			if (existedUsername_Abonne == null) {
				 //TODO change with constructor later
				
				 
				Abonne abonne = new Abonne();
				abonne.setUsername(username);
				abonne.setFirstname(firstname);
				abonne.setLastname(lastname);
				abonne.setEmail(email);
				abonne.setPassword(password);
				
				 if (image != null ) {
					   
					    byte[] img_ = org.apache.commons.codec.binary.Base64.decodeBase64(image) ;//Base64.getDecoder().decode(img); 
					    abonne.setImage(img_);
						//ShowProfileService.uploadProfilePicture(created_abonne_response_json.get("
				  }
				
				Set<CitiesOfInterest> citiesOfInterests = new HashSet<>();
				for (int i = 0; i < cities.length; i++) {
					// TODO verifier si la ville n'existe pas dans la bdd avant d'ajouter
					// TODO cas juste pour le test 
					// TODO cas reel si la ville exite on ajouter l'abonne dans la liste des abonnes de la ville 
					CitiesOfInterest city = new CitiesOfInterest();
					city.setCity_name(cities[i]);
					citiesOfInterests.add(city);
					//CitiesOfInterestDAO.addCityOfInterest(city);
					//CitiesOfInterestDAO.userOfCity(abonne.getABONNE_id(), city.getCity_id());

					//abonne.getCities().add(city);
				}
				
				abonne.setCities(citiesOfInterests);
				AbonneDAO.addAbonne(abonne);
				//System.out.println("**************************************************"+abonne.toString());
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Abonne loginAbonne(String email, String password){
		
		// TODO replace by getAbonneByUsernameAndPassword
		Abonne abonne = AbonneDAO.getAbonneByUserNameAndPassword(email,password);
		return abonne;
	}
}
