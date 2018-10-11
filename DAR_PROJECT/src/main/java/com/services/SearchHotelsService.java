package com.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.api.AmadeusHotelsApiAccess;
import com.api.GoogleMapApiAccess;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.maps.model.LatLng;

import helpers.models.SearchRequestModel;

public class SearchHotelsService {
	public JsonObject getHotels(SearchRequestModel searchObject) {
		
		/*
		 * Call Google Api To get longitude and latitude by city name
		 * */
		
		LatLng googleGeocode;
		String amadeusResponse;
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
			amadeusResponse = AmadeusHotelsApiAccess.getHotelOffers
					(formatter.format(c.getTime()),
					formatter.format(c1.getTime()),
					searchObject.getRadius(), 
					searchObject.getPrice(),
					String.valueOf(googleGeocode.lat),
					String.valueOf(googleGeocode.lng));
			
			JsonParser parser = new JsonParser();
			JsonObject o = parser.parse(amadeusResponse.toString()).getAsJsonObject();
			return o;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
