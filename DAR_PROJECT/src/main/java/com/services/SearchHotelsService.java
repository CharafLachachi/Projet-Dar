package com.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import com.amadeus.resources.HotelOffer;
import com.amadeus.resources.HotelOffer.Offer;
import com.api.AmadeusHotelsApiAccess;
import com.api.AmadeusHotelsInformationApiAcess;
import com.api.GoogleMapApiAccess;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.maps.model.LatLng;

import helpers.models.SearchRequestModel;
import helpers.models.SearchResponseModel;

public class SearchHotelsService {
	public String getHotels(SearchRequestModel searchObject) {
		 String checkInDate;
		 String checkOutDate;
		/*
		 * Call Google Api To get longitude and latitude by city name
		 * */
		/*TODO refactor code */
		LatLng googleGeocode;
		HotelOffer[] amadeusResponse;
		Gson gson = new Gson();
		
		// Required format by amadeus api
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		// To format iso date sent by client as 2018-10-12T22:00:00.000Z
		Calendar c = javax.xml.bind.DatatypeConverter.parseDateTime(searchObject.getChekInDate()) ;
		Calendar c1 = javax.xml.bind.DatatypeConverter.parseDateTime(searchObject.getCheckOutDate()) ;
		
		
		try {
			// Get City Geocode Json from google APi
			googleGeocode = GoogleMapApiAccess.
					getCityGeoCodeByCityName("Paris, France");
			// Get Hotels results 
			checkInDate = formatter.format(c.getTime());
			checkOutDate = 	formatter.format(c1.getTime());
			amadeusResponse = AmadeusHotelsApiAccess.getHotelOffers(
					checkInDate,
					checkOutDate,
					searchObject.getRadius(), 
					searchObject.getPrice(),
					String.valueOf(googleGeocode.lat),
					String.valueOf(googleGeocode.lng));
			
			// Get Offers from Hotels results
			List<SearchResponseModel> searchResponseModels = new ArrayList<SearchResponseModel>();
			
			for (HotelOffer hotelOffer : amadeusResponse) {
			
				String hotelId = hotelOffer.getHotel().getHotelId();
				StringBuffer hotelInfoJson = AmadeusHotelsInformationApiAcess.
						GetResponseFromAPI(
								AmadeusHotelsInformationApiAcess.
								getHotelInfoById(hotelId, checkInDate, checkOutDate));
				JsonObject jsonObject = gson.fromJson(hotelInfoJson.toString(), JsonObject.class);
				String hotelName = jsonObject.get("property_name").getAsString();
				String hotelAddress = jsonObject.get("address").getAsJsonObject().toString();
				
				String hotelContacts = jsonObject.get("contacts").getAsJsonArray().toString();
				
				for (Offer offer: hotelOffer.getOffers()) {
					
					
					// Create response model which will be send to the client
					SearchResponseModel offerResponse = new SearchResponseModel();
					offerResponse.setRoompPrice(offer.getPrice().getTotal());
					offerResponse.setChekInDate(checkInDate);
					offerResponse.setCheckOutDate(checkOutDate);
					offerResponse.setRadius(searchObject.getRadius());
					offerResponse.setNbPers(searchObject.getNbPers());
					offerResponse.setCity(searchObject.getCityName());
					offerResponse.setAddress(hotelAddress);
					offerResponse.setHotelName(hotelName);
					offerResponse.setHotelContacts(hotelContacts);
					searchResponseModels.add(offerResponse);
					
				}
				
			}
			
			
			
			//JsonParser parser = new JsonParser();
			//JsonObject o = parser.parse(amadeusResponse.toString()).getAsJsonObject();
			return gson.toJson(searchResponseModels);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
