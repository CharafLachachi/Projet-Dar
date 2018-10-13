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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.maps.model.LatLng;

import helpers.models.AddressModel;
import helpers.models.HotelContactModel;
import helpers.models.SearchRequestModel;
import helpers.models.SearchResponseModel;

/*
 * 
 * @author Lachachi charaf
 * 
 * 
 * */
public class SearchHotelsService {

	public String getHotels(SearchRequestModel searchObject) {
		String checkInDate;
		String checkOutDate;
		Calendar calendarIn;
		Calendar calendarOut;
		LatLng googleGeocode;
		HotelOffer[] amadeusResponse;
		Gson gson = new Gson();

		// Required format by amadeus api
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		// To format iso date sent by the client as 2018-10-12T22:00:00.000Z
		calendarIn = javax.xml.bind.DatatypeConverter.parseDateTime(searchObject.getChekInDate());
		calendarOut = javax.xml.bind.DatatypeConverter.parseDateTime(searchObject.getCheckOutDate());
		checkInDate = formatter.format(calendarIn.getTime());
		checkOutDate = formatter.format(calendarOut.getTime());

		try {
			// Get City Geocode Json from google APi
			googleGeocode = getCityGeoCodeByName(searchObject.getCityName());

			// Get Hotels results
			amadeusResponse = AmadeusHotelsApiAccess.getHotelOffers(checkInDate, checkOutDate, searchObject.getRadius(),
					searchObject.getPrice(), String.valueOf(googleGeocode.lat), String.valueOf(googleGeocode.lng));

			// Get Offers from Hotels results
			List<SearchResponseModel> searchResponseModels = new ArrayList<SearchResponseModel>();

			for (HotelOffer hotelOffer : amadeusResponse) {
				// Get hotel information from Google API
				StringBuffer hotelInfoJson = getHotelInformationsById(hotelOffer.getHotel().getHotelId(), checkInDate,
						checkOutDate);

				// Parse stringbuffer as json object to get informations properties
				JsonObject jsonObject = gson.fromJson(hotelInfoJson.toString(), JsonObject.class);
				JsonObject hotelAddress = null;
				String hotelName = jsonObject.get("property_name").getAsString();
				if (jsonObject.has("address") == false || hotelName.toLowerCase().contains("Missing")) {
					continue;
				}
				hotelAddress = jsonObject.get("address").getAsJsonObject();
				JsonArray hotelContacts = jsonObject.get("contacts").getAsJsonArray();

				for (Offer offer : hotelOffer.getOffers()) {

					// Ignore offer if price is empty or null
					if (offer.getPrice().getTotal() == null || offer.getPrice().getTotal().isEmpty())
						continue;

					// Create response model which will be send to the client
					SearchResponseModel offerResponse = new SearchResponseModel();
					offerResponse.setRoomPrice(offer.getPrice().getTotal());
					offerResponse.setChekInDate(checkInDate);
					offerResponse.setCheckOutDate(checkOutDate);
					offerResponse.setRadius(searchObject.getRadius());
					offerResponse.setNbPers(searchObject.getNbPers());
					offerResponse.setCity(searchObject.getCityName());

					AddressModel hotelAdr = new AddressModel();
					if (!hotelAddress.get("city").getAsString().isEmpty()
							&& !hotelAddress.get("postal_code").getAsString().isEmpty()
							&& !hotelAddress.get("line1").getAsString().isEmpty()) {
						hotelAdr.setCity(hotelAddress.get("city").getAsString());
						hotelAdr.setPostal_code(hotelAddress.get("postal_code").getAsString());
						hotelAdr.setLine1(hotelAddress.get("line1").getAsString());
					}
					offerResponse.setAddress(hotelAdr);
					offerResponse.setHotelName(hotelName);

					HotelContactModel hotelContact = new HotelContactModel();
					for (JsonElement elemnt : hotelContacts) {
						JsonObject elementObject = elemnt.getAsJsonObject();
						if (elementObject.has("type")) {
							
							String type = elementObject.get("type").getAsString();
							switch (type) {
							case "PHONE":
								hotelContact.setTel(elementObject.get("detail").getAsString());
								break;
							case "URL":
								hotelContact.setUrl(elementObject.get("detail").getAsString());
								break;
							case "EMAIL":
								hotelContact.setEmail(elementObject.get("detail").getAsString());
								break;
							default:
								break;
							}
						}
					}
					offerResponse.setHotelContacts(hotelContact);

				

					System.out.println(offerResponse.toString());
					searchResponseModels.add(offerResponse);

				}

			}
			// Return List of offers Models ready to be sent to the client
			return gson.toJson(searchResponseModels);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Get City Geocode Json from google APi
	private LatLng getCityGeoCodeByName(String cityName) {
		return GoogleMapApiAccess.getCityGeoCodeByCityName(cityName);
	}

	private StringBuffer getHotelInformationsById(String hotelId, String checkInDate, String checkOutDate) {
		try {
			return AmadeusHotelsInformationApiAcess.GetResponseFromAPI(
					AmadeusHotelsInformationApiAcess.getHotelInfoById(hotelId, checkInDate, checkOutDate));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
